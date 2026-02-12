package com.wipro.payroll.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	Administrator admin = new Administrator();

	public String addRecord(HttpServletRequest request) {

		PayrollBean bean = new PayrollBean();

		bean.setEmployeeName(request.getParameter("employeeName"));
		bean.setDesignation(request.getParameter("designation"));
		bean.setDepartment(request.getParameter("department"));
		bean.setRemarks(request.getParameter("remarks"));

		int salary = Integer.parseInt(request.getParameter("salary"));
		Date paymentDate = java.sql.Date.valueOf(request.getParameter("paymentDate"));
		bean.setPaymentDate(paymentDate);

		return admin.addRecord(bean);
	}

	public PayrollBean viewRecord(HttpServletRequest request) {

		String employeeName = request.getParameter("employeeName");
		Date paymentDate = java.sql.Date.valueOf(request.getParameter("paymentDate"));

		return admin.viewRecord(employeeName, paymentDate);
	}

	public List<PayrollBean> viewAllRecords(HttpServletRequest request) {
		return admin.viewAllRecords();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation");

		try {
			if (operation.equals("newRecord")) {
				String result = addRecord(request);

				if (result == null || result.equals("FAIL")) {
					response.sendRedirect("error.html");
				} else {
					response.sendRedirect("success.html");
				}
			} else if (operation.equals("viewRecord")) {
				PayrollBean bean = viewRecord(request);

				if (bean == null) {
					response.sendRedirect("displayPayroll.jsp?message=No matching records exists! Please try again!");
				} else {
					request.setAttribute("payroll", bean);
					RequestDispatcher rd = request.getRequestDispatcher("displayPayroll.jsp");
					rd.forward(request, response);
				}
			} else if (operation.equals("viewAllRecords")) {

				List<PayrollBean> list = viewAllRecords(request);

				if (list == null || list.isEmpty()) {
					response.sendRedirect("displayAllPayrolls.jsp?message=No records available!");
				} else {
					request.setAttribute("payrollList", list);
					RequestDispatcher rd = request.getRequestDispatcher("displayAllPayrolls.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			response.sendRedirect("error.html");
		}
	}
}
