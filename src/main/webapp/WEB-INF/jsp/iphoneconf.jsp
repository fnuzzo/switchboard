<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>iPhone Configuration</title>
    </head>
    <body>
        <h1>iPhone Configuration</h1>
        <form method="post" action="/switchboard/profiles/edit/iphone?id=${profileAttribute.id}" enctype="multipart/form-data">
            <p><input type="file" name="file"/></p>
            <p>password: <input type="text" name="password"/></p>
            <p><input type="submit"/></p>
        </form>
    </body>
</html>