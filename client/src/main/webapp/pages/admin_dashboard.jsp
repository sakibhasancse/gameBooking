<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Admin Dashboard</title></head>
<body>
<h2>Welcome, ${username} (Admin)</h2>

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

<h3>Player Booking Requests</h3>
<c:if test="${not empty requests}">
    <ul>
        <c:forEach var="req" items="${requests}">
            <li>
                    ${req}
                <!-- Add approve button (example, needs action mapping on backend) -->
                <form method="post" action="admin/approveRequest">
                    <input type="hidden" name="requestId" value="${req.id}"/>
                    <button type="submit">Approve</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty requests}">
    <p>No pending player requests.</p>
</c:if>

<!-- Link to create new game -->
<a href="admin/createGame">Create New Game</a><br><br>
<a href="logout">Logout</a>
</body>
</html>
