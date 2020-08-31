<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <c:if test="${empty user.username}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.username}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.username}">
    <c:url value="/systems/add" var="var"/>
</c:if>
<c:if test="${!empty user.username}">
    <c:url value="/systems/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty user.username}">
    <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">

    <label for="lastName">lastName</label>
    <input type="text" name="lastName" id="lastName">

    <label for="age">Age</label>
    <input type="text" name="age" id="age">

    <label for="password">Password</label>
    <input type="password" name="password" id="password">

        <c:if test="${empty user.username}">
            <input type="submit" value="Add new user">
        </c:if>
        <c:if test="${!empty user.username}">
    <input type="submit" value="Edit user">
        </c:if>
</form>
</body>
</html>