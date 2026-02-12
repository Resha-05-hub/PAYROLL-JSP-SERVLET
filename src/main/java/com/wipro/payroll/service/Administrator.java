package com.wipro.payroll.service;

import java.sql.Date;
import java.util.List;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.dao.PayrollDAO;
import com.wipro.payroll.util.InvalidInputException;

public class Administrator {
	PayrollDAO dao = new PayrollDAO();

	public String addRecord(PayrollBean payrollBean) {
		try {
			if (payrollBean == null || payrollBean.getEmployeeName() == null || payrollBean.getPaymentDate() == null) {
				throw new InvalidInputException();
			}
			if (payrollBean.getEmployeeName().length() < 2) {
				return "INVALID EMPLOYEE NAME";
			}
			Date today = new Date(System.currentTimeMillis());
			if (payrollBean.getPaymentDate().after(today)) {
				return "INVALID DATE";
			}
			if (dao.recordExists(payrollBean.getEmployeeName(), payrollBean.getPaymentDate())) {
				return "ALREADY EXISTS";
			}
			String recordId = dao.generateRecordID(payrollBean.getEmployeeName(), payrollBean.getPaymentDate());
			payrollBean.setRecordId(recordId);

			String status = dao.createRecord(payrollBean);

			return status;
		} catch (InvalidInputException e) {
			return "INVALID INPUT";
		}
	}

	public PayrollBean viewRecord(String employeeName, Date paymentDate) {
		return dao.fetchRecord(employeeName, paymentDate);
	}

	public List<PayrollBean> viewAllRecords() {
		return dao.fetchAllRecords();
	}
}
