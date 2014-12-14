<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Form</title>
<link rel="stylesheet"
    href="//ajax.googleapis.com/ajax/libs/dojo/1.10.1/dojo/resources/dojo.css" />
<link rel="stylesheet"
    href="//ajax.googleapis.com/ajax/libs/dojo/1.10.1/dijit/themes/claro/claro.css" />
<link rel="stylesheet"
    href="//ajax.googleapis.com/ajax/libs/dojo/1.10.1/dojox/grid/enhanced/resources/claro/EnhancedGrid.css" />
<link rel="stylesheet"
    href="//ajax.googleapis.com/ajax/libs/dojo/1.10.1/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css" />
<script src="//ajax.googleapis.com/ajax/libs/dojo/1.10.1/dojo/dojo.js"
            data-dojo-config="parseOnLoad: true"></script>
<script type="text/javascript" src="contact.js"></script>
</head>
<body class="claro">
<center>
	<h1  align="center">Contact form</h1>
	<div id="message" style="color:red"></div>
	<table>
		<tr>
			<td align="right">
				First name: 
			</td>
			<td align="left">
				<input type="text" id="firstName" data-dojo-type="dijit/form/TextBox" data-dojo-props="trim:true"  required="true">
			</td>
		</tr>
		<tr>
			<td align="right">
				Last name: 
			</td>
			<td align="left">
				<input type="text" id="lastName" data-dojo-type="dijit/form/TextBox" data-dojo-props="trim:true"  required="true">
			</td>
		</tr>
		<tr>
			<td align="right">
				Email: 
			</td>
			<td align="left">
				<input type="text" id="email" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,validator:dojox.validate.isEmailAddress,invalidMessage:'Please enter a valid email!'"
				 required="true">
			</td>
		</tr>
		<tr>
			<td align="right">
				Phone: 
			</td>
			<td align="left">
				<input type="text" id="phone" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'\\d{10}', invalidMessage:'Please enter a 10 digit phone number.'"  required="true">
			</td>
		</tr>
		<tr>
			<td align="right">
				Date of birth: 
			</td>
			<td align="left">
				<input type="text" id="dob" constraints="{datePattern : 'dd-MM-yyyy'}" data-dojo-type="dijit/form/DateTextBox"  required="true">
			</td>
		</tr>
		
	</table>
	<table>
		<tr>
			<td>
				<input id="create" type="button" value="Create" >
				<input id="update" type="button" value="Update" >
				<input id="delete" type="button" value="Delete" >
				<input id="reset" type="button" value="Reset"  >
			</td>
		</tr>
	</table>
	<div id="grid"></div>
</center>
</body>
</html>