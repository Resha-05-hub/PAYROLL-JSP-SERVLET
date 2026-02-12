<!DOCTYPE html>
<html>
<head>
<title>Add Payroll</title>
</head>
<body>
	<h2>Add Payroll Record</h2>
	<form action="MainServlet" method="post">
		<input type="hidden" name="operation" value="newRecord">
		Employee Name: <input type="text" name="employeeName"><br>
		<br> Designation: <input type="text" name="designation"><br>
		<br> Department: <input type="text" name="department"><br>
		<br> Salary: <input type="text" name="salary"><br>
		<br> Payment Date: <input type="date" name="paymentDate"><br>
		<br> Remarks: <input type="text" name="remarks"><br>
		<br> <input type="submit" value="Add Record">
	</form>
</body>
</html>
