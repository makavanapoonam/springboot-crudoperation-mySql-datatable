<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="eId" value="${eId}"/>
					<h1 align="center" >Add / Edit Employee:</h1>
					<table id="add-div" align="center">
						<tr>
							<td>name:</td> 
							<td><input type="text" id="name" value="${name}" /></td>
						</tr>
						<tr>
							<td>city:</td> 
							<td><input type="text" id="city" value="${city}" /> </td>
						</tr>
						<tr> 
							<td>address:</td> 
							<td><input type="text" id="address" value="${address}" /></td>
						</tr>
						<tr>
						 	<td>mobile:</td> 
						 	<td><input type="text" id="mobile" value="${mobile}" /></td>
						</tr> 
						<tr><td colspan="2" align="center">
						<a id="save" href="#">Submit</a></td>
						</tr>
						</table>
						
						<div id="add-div" style="display: none">
						<table align="center">
							<tr>
								<td>name:</td> 
								<td><input type="text" id="name" value="${name}" placeholder="name" /></td>
							</tr>
							<tr>
								<td>city:</td> 
								<td><input type="text" id="city" value="${city}" /> </td>
							</tr>
							<tr> 
								<td>address:</td> 
								<td><input type="text" id="address" value="${address}" /></td>
							</tr>
							<tr>
							 	<td>mobile:</td> 
							 	<td><input type="text" id="mobile" value="${mobile}" /></td>
							</tr> 
							<tr><td colspan="2" align="center">
							<a id="save" href="#">Submit</a></td>
							</tr>
							</table>
						</div>
</body>
</html>