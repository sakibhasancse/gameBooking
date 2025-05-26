<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 3/21/2025
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login Page</h2>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<p style="color: red;"><%= error %></p>
<% } %>

<form action="/user/login" method="post">
    <label>Username: <input name="username" type="text" required></label><br>
    <label>Password: <input name="password" type="password" required></label><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
