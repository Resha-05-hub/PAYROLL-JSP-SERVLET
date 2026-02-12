<%@ page import="com.wipro.payroll.bean.PayrollBean"%>

<!DOCTYPE html>
<html>
<head>
<title>Display Payroll</title>
</head>
<body>
	<h2>Payroll Details</h2>
	<%
	PayrollBean bean = (PayrollBean) request.getAttribute("payroll");
	if (bean != null) {
	%>
	Employee Name:
	<%=bean.getEmployeeName()%><br>
	<br> Designation:
	<%=bean.getDesignation()%><br>
	<br> Department:
	<%=bean.getDepartment()%><br>
	<br> Salary:
	<%=bean.getSalary()%><br>
	<br> Payment Date:
	<%=bean.getPaymentDate()%><br>
	<br> Remarks:
	<%=bean.getRemarks()%><br>
	<br>
	<%
	} else {
	%>
	<h3>No matching records exists! Please try again!</h3>
	<%
	}
	%>
</body>
</html>