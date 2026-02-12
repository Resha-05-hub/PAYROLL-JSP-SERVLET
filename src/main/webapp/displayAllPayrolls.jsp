<%@ page import="java.util.List"%>
<%@ page import="com.wipro.payroll.bean.PayrollBean"%>

<!DOCTYPE html>
<html>
<head>
<title>All Payroll Records</title>
</head>
<body>
	<h2>All Payroll Records</h2>
	<%
	String message = request.getParameter("message");
	if (message != null) {
	%>
	<h3><%=message%></h3>
	<%
	} else {
	List<PayrollBean> list = (List<PayrollBean>) request.getAttribute("payrollList");
	if (list != null && !list.isEmpty()) {
		for (PayrollBean bean : list) {
	%>
	<hr>
	Employee Name:
	<%=bean.getEmployeeName()%><br> Designation:
	<%=bean.getDesignation()%><br> Department:
	<%=bean.getDepartment()%><br> Salary:
	<%=bean.getSalary()%><br> Payment Date:
	<%=bean.getPaymentDate()%><br> Remarks:
	<%=bean.getRemarks()%><br>
	<%
	}
	} else {
	%>
	<h3>No records available!</h3>
	<%
	}
	}
	%>

</body>
</html>