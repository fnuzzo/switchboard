<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Added Page</title>
</head>
<body>

<h1>profiles</h1>

<p>You have added a new profile at <%= new java.util.Date() %></p>
<p>Youk Client KEY is:  ${client_key}</p>
<p>Youk Device KEY is:  ${device_key}</p>


<c:url var="mainUrl" value="/switchboard/profiles" />
<p>Return to <a href="${mainUrl}">Main List</a></p>

</body>
</html>