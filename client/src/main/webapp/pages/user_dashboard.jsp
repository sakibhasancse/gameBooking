<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>User Dashboard</title></head>
<body>
<h2>Welcome, ${username} (User)</h2>

<h3>Available Games</h3>
<c:if test="${not empty games}">
    <ul>
        <c:forEach var="game" items="${games}">
            <li>${game}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty games}">
    <p>No games available.</p>
</c:if>

<h3>Your Booking Requests</h3>
<c:if test="${not empty requests}">
    <ul>
        <c:forEach var="req" items="${requests}">
            <li>${req}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty requests}">
    <p>You have no booking requests.</p>
</c:if>

<a href="logout">Logout</a>
</body>
</html>
