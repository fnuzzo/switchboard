<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile Configuration</title>
</head>
<body>

<h1>Edit profile</h1>

<c:url var="saveUrl" value="/switchboard/profiles/edit?id=${profileAttribute.id}" />
<c:url var="andUrl" value="/switchboard/profiles/edit/android?id=${profileAttribute.id}" />
<c:url var="bbUrl" value="/switchboard/profiles/edit/blackberry?id=${profileAttribute.id}" />
<c:url var="iosUrl" value="/switchboard/profiles/edit/iphone?id=${profileAttribute.id}" />


<form:form modelAttribute="profileAttribute" method="POST" action="${saveUrl}">
		<table>
		<tr>
			<td><form:label path="name">Name:</form:label></td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
			<td><form:label path="email">Email</form:label></td>
			<td><form:input path="email"/></td>
		</tr>
		<tr>
			<td><form:label path="device_key">Device key:</form:label></td>
			<td><form:input path="device_key"/></td>
		</tr>
		<tr>
			<td><form:label path="client_key">Client key:</form:label></td>
			<td><form:input path="client_key"/></td>
		</tr>
		</table>
		
	<table>
		<tr>
			<td><a href="${andUrl}">Andndroid Config</a></td>
		</tr>
		<tr>
			<td><a href="${bbUrl}">BlackBerry Config</a></td>
		</tr>
		<tr>
			<td><a href="${iosUrl}">iPhone Config</a></td>
		</tr>
		
	</table>
	<input type="submit" value="Save" />
</form:form>

</body>
</html>