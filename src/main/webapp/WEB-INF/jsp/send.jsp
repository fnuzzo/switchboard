<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Devices List</title>
</head>
<body>
<h1>Send Notification</h1>

<c:url var="addUrl" value="/switchboard/profiles/devicelist" />
<c:url var="sendMessage" value="/switchboard/profiles/devicelist?id=${profileAttribute.id}" />
<form:form modelAttribute="messageAttribute" method="POST" action="${sendMessage}">

<table style="border: 1px solid; width: 500px; text-align:center">
	<thead>
		<tr>
			
			<th>Type</th>
			<th>Os version</th>
			<th> API Version</th>	
			<th>Action</th>			
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${devices}" var="device">
			
		<tr>
			<td><c:out value="${device.type}" /></td>
			<td><c:out value="${device.osVersion}" /></td>
			<td><c:out value="${device.apiVersion}" /></td>
			<td><a href="${deleteUrl}">Delete</a></td>
  	</c:forEach> 
	</tbody>
</table>

<table>
		<tr>
			<td><form:label path="message">Message:</form:label></td>
			<td><form:input path="message"/><input type="submit" value="Send" /></td>
		</tr>
</table>		
	
</form:form>		
<c:if test="${empty devices}">
	There are currently no devices in the list.
</c:if>

<c:url var="mainUrl" value="/switchboard/profiles" />
<p>Return to <a href="${mainUrl}">Main List</a></p>
</body>
</html>