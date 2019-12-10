<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/> -->
<!-- <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
 --><meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Employee Data:</h1>
		<a id="add" href="#">Add Employee</a>		
					<div id="add-div" style="display: none">
						<table align="center">
							<tr>
								<td><input type="text" id="name" value="${name}" placeholder="name" />
								<input type="text" id="city" value="${city}" placeholder="city" />
								<input type="text" id="address" value="${address}" placeholder="address"/>
							 	<input type="text" id="mobile" maxlength="10" value="${mobile}" placeholder="mobile" />
							<a id="save" href="#">Save</a></td>
							</tr>
							</table>
						</div>
<!-- <h1 align="center">Insert / Update / Delete / Display:</h1> -->
<!-- <table align="center">
<tr>
<td>
<select class="selectpicker" id="selectct">
							 	<option value="1" selected="selected">Add</option>
							 	<option value="2">Update</option>
							 	<option value="3">Delete</option>
							 	<option value="4">Display</option>
						 		</select>
						 		</td>
						 		</tr>
</table> -->

					<table id="dbexample" align="center" class="display" width="80%" border="1">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>City</th>
								<th>Address</th>
								<th>Mobile</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
						
						<div id="edit-div" style="display: none">
							<table align="center">
							<tr>
								<td>enter id:</td>
								<td> <input type="text" id="sid" />
								</td>
							</tr>
							<tr><td colspan="2" align="center">
							<a id="edit" href="#">Submit</a></td>
							</tr>
							</table>
						</div>
						
</body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

<!-- <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
<script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> -->

<script type="text/javascript" src="/employee/js/employee.js"></script>		
</html>