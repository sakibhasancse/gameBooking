<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/23/2025
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create Student</title>
</head>
<body>
<h2>Create New Student</h2>

<form action="/user/student-create" method="post">
  User Name: <input type="text" name="username" required /><br/>
  Full Name: <input type="text" name="name" required /><br/>
  Email: <input type="email" name="email" required /><br/>
  Phone: <input type="text" name="phone" required /><br/>
  Gender:
  <select name="gender">
    <option value="Male">Male</option>
    <option value="Female">Female</option>
  </select><br/>
  Address: <input type="text" name="address" /><br/>
  Department: <input type="text" name="department" /><br/>
  Registration Number: <input type="text" name="registrationNumber" required />
  <br/>
  <input type="submit" value="Create Student" />
</form>

<br>
<a href="/">Back to Home</a>
</body>
</html>