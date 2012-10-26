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

<h1>Android Configuration</h1>

<c:url var="saveUrl" value="/switchboard/profiles/edit/android?id=${profileAttribute.id}" />
<form:form modelAttribute="andConfig" method="POST" action="${saveUrl}">

	<table>
		<tr>			
			<td><form:label path="gcm_key">Android API KEY:</form:label></td>
			<td><form:input path="gcm_key"/></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>