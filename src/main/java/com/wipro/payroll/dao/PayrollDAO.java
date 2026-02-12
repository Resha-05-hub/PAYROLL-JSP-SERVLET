package com.wipro.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.util.DBUtil;

public class PayrollDAO {
	public String createRecord(PayrollBean payrollBean) {
		String recordId = generateRecordID(payrollBean.getEmployeeName(), payrollBean.getPaymentDate());
		String sql = "INSERT INTO PAYROLL_TB VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = DBUtil.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, recordId);
			ps.setString(2, payrollBean.getEmployeeName());
			ps.setString(3, payrollBean.getDesignation());
			ps.setDate(4, new Date(payrollBean.getPaymentDate().getTime()));
			ps.setInt(5, payrollBean.getSalary());
			ps.setString(6, payrollBean.getDepartment());
			ps.setString(7, payrollBean.getRemarks());

			int rows = ps.executeUpdate();

			if (rows > 0)
				return recordId;
			else
				return "FAIL";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}

	public PayrollBean fetchRecord(String employeeName, Date paymentDate) {
		String sql = "SELECT * FROM PAYROLL_TB WHERE EMPLOYEENAME=? AND PAYMENT_DATE=?";
		try (Connection con = DBUtil.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, employeeName);
			ps.setDate(2, new Date(paymentDate.getTime()));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				PayrollBean bean = new PayrollBean();
				bean.setRecordId(rs.getString("RECORDID"));
				bean.setEmployeeName(rs.getString("EMPLOYEENAME"));
				bean.setDesignation(rs.getString("DESIGNATION"));
				bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				bean.setSalary(rs.getInt("SALARY"));
				bean.setDepartment(rs.getString("DEPARTMENT"));
				bean.setRemarks(rs.getString("REMARKS"));

				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String generateRecordID(String employeeName, Date paymentDate) {
		String recordId = "";
		try (Connection con = DBUtil.getDBConnection()) {

			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			String datePart = format.format(paymentDate);
			String namePart = employeeName.substring(0, 2).toUpperCase();
			String seqSql = "SELECT PAYROLL_SEQ.NEXTVAL FROM DUAL";
			PreparedStatement ps = con.prepareStatement(seqSql);
			ResultSet rs = ps.executeQuery();

			int seqValue = 0;

			if (rs.next()) {
				seqValue = rs.getInt(1);
			}
			recordId = datePart + namePart + seqValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordId;
	}

	public boolean recordExists(String employeeName, Date paymentDate) {
		String sql = "SELECT 1 FROM PAYROLL_TB WHERE EMPLOYEENAME=? AND PAYMENT_DATE=?";
		try (Connection con = DBUtil.getDBConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, employeeName);
			ps.setDate(2, new Date(paymentDate.getTime()));

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<PayrollBean> fetchAllRecords() {
		List<PayrollBean> list = new ArrayList<>();
		String sql = "SELECT * FROM PAYROLL_TB";
		try (Connection con = DBUtil.getDBConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				PayrollBean bean = new PayrollBean();

				bean.setRecordId(rs.getString("RECORDID"));
				bean.setEmployeeName(rs.getString("EMPLOYEENAME"));
				bean.setDesignation(rs.getString("DESIGNATION"));
				bean.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				bean.setSalary(rs.getInt("SALARY"));
				bean.setDepartment(rs.getString("DEPARTMENT"));
				bean.setRemarks(rs.getString("REMARKS"));

				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
