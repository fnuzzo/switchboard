<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Profiles</title>
</head>
<body>
<h1>profiles</h1>

<c:url var="addUrl" value="/switchboard/profiles/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Device Key</th>
			<th>Client Key</th>
			<th>Action</th>
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${profiles}" var="profile">
			<c:url var="editUrl" value="/switchboard/profiles/edit?id=${profile.id}" />
			<c:url var="deleteUrl" value="/switchboard/profiles/delete?id=${profile.id}" />
			<c:url var="addDeviceUrl" value="/switchboard/profiles/addDevice?id=${profile.id}" />
			<c:url var="listDeviceUrl" value="/switchboard/profiles/devicelist?id=${profile.id}" />
		<tr>
			<td><c:out value="${profile.id}" /></td>
			<td><c:out value="${profile.name}" /></td>
			<td><c:out value="${profile.email}" /></td>
			<td><c:out value="${profile.device_key}" /></td>
			<td><c:out value="${profile.client_key}" /></td>
			
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${listDeviceUrl}">List associated Devices</a></td>
			
		</tr>
	</c:forEach>
	</tbody>
</table>
<c:if test="${empty profiles}">
	There are currently no profiles in the list.
</c:if>
	<td><a href="${addUrl}">Add</a></td>
	
	
</body>
</html>