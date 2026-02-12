<!DOCTYPE html>
<html>
<head>
<title>View Payroll</title>
</head>
<body>
	<h2>View Payroll Record</h2>
	<form action="MainServlet" method="post">
		<input type="hidden" name="operation" value="viewRecord">
		Employee Name: <input type="text" name="employeeName"><br>
		<br> Payment Date: <input type="date" name="paymentDate"><br>
		<br> <input type="submit" value="View Record">
	</form>
</body>
</html>
