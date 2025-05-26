<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 12:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Games</title></head>
<body>
<h2>Available Games</h2>
<c:forEach var="game" items="${games}">
    <p>${game}</p>
</c:forEach>

<c:if test="${role == 'ADMIN'}">
    <h3>Player Requests</h3>
    <c:forEach var="req" items="${requests}">
        <p>${req}</p>
    </c:forEach>
    <a href="games/create">Create New Game</a>
</c:if>

<a href="logout">Logout</a>
</body>
</html>
