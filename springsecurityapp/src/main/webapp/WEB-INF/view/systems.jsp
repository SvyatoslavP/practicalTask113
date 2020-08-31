<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title>luv2code SYSTEMS Home Page</title>
	<link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>

<h2>luv2code SYSTEMS Home Page</h2>

<hr>

<p>
	We have our annual holiday Caribbean cruise coming up. Register now!
	<br>
	Keep this trip a secret, don't tell the regular employees LOL :-)
</p>

<hr>

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
			<td>${user.username}</td>
			<td>${user.lastName}</td>
			<td>${user.age}</td>
			<td>

				<a href="/systems/edit/${user.id}">edit</a>
				<a href="/systems/delete/${user.id}">delete</a>
			</td>
		</tr>
	</c:forEach>
</table>
<h2>Add</h2>
<c:url value="/systems/add" var="add"/>
<a href="${add}">add new user</a>
<c:forEach begin="1" end="${pageCount}" step="1" varStatus="i">
	<c:url value="" var="url">
		<c:param name="page" value="${i.index}"/>
	</c:url>
	<a href="${url}">${i.index}</a>
</c:forEach>
<hr>
<a href="${pageContext.request.contextPath}/employees">Back to Home Page</a>

</body>

</html>









