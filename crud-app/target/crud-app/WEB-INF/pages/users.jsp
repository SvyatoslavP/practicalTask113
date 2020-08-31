<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>lastname</th>
        <th>age</th>
        <th>action</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>
            <a href="/edit/${user.id}">edit</a>
            <a href="/delete/${user.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
    <h2>Add</h2>
    <c:url value="/add" var="add"/>
    <a href="${add}">add new user</a>
    <c:forEach begin="1" end="${pageCount}" step="1" varStatus="i">
    <c:url value="/" var="url">
    <c:param name="page" value="${i.index}"/>
        </c:url>
    <a href="${url}">${i.index}</a>
        </c:forEach>
</body>
</html>