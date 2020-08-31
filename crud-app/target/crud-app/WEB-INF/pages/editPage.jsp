<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty user.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty user.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty user.name}">
    <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="lastName">lastName</label>
    <input type="text" name="lastName" id="lastName">
    <label for="age">Age</label>
    <input type="text" name="age" id="age">
        <c:if test="${empty user.name}">
            <input type="submit" value="Add new user">
        </c:if>
        <c:if test="${!empty user.name}">
    <input type="submit" value="Edit user">
        </c:if>
</form>
</body>
</html>