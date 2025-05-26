<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Create Game</title></head>
<body>
<h2>Create New Game</h2>
<form method="post" action="createGame">
    Name: <input type="text" name="name" required/><br/>
    Description: <input type="text" name="description" required/><br/>
    <button type="submit">Create</button>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<a href="admin/dashboard">Back to Dashboard</a>
</body>
</html>