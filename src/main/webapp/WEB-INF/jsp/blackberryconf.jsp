<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Android Configuration</title>
</head>
<body>

<c:url var="saveUrl" value="/switchboard/profiles/edit/blackberry?id=${profileAttribute.id}" />
<form:form modelAttribute="bbConfig" method="POST" action="${saveUrl}">
<table>
		<tr>
			<td><form:label path="rmi_key">API ID:</form:label></td>
			<td><form:input path="rmi_key"/></td>
		</tr>
	<tr>
			<td><form:label path="rmi_password">Password:</form:label></td>
			<td><form:input path="rmi_password" /></td>
		</tr>
		
		<tr>
			<td><form:label path="rmi_url">PPG BASE URL:</form:label></td>
			<td><form:input path="rmi_url" /></td>
		</tr>
		</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>