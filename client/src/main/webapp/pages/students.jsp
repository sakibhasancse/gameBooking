<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/23/2025
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.seu.javaFriday.Student" %>


<%
  Boolean isLogin = (Boolean) session.getAttribute("login");
  if (isLogin == null || !isLogin) {
%>
<h2>You are not logged in!</h2>
<a href="/user/login">Login</a>
<%
} else {
  List<Student> students = (List<Student>) request.getAttribute("students");
%>

<html>
<head>
  <title>Student List</title>
</head>
<body>
<h2>All Students</h2>

<%
  if (students != null && !students.isEmpty()) {
%>
<table border="1" cellpadding="8">
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Gender</th>
    <th>Department</th>
    <th>Registration No.</th>
  </tr>
  <%
    for (Student student : students) {
  %>
  <tr>
    <td><%= student.getName() %></td>
    <td><%= student.getEmail() %></td>
    <td><%= student.getPhone() %></td>
    <td><%= student.getGender() %></td>
    <td><%= student.getDepartment() %></td>
    <td><%= student.getRegistrationNumber() %></td>
  </tr>
  <%
    }
  %>
</table>
<%
} else {
%>
<p>No students found.</p>
<%
  }
%>

<br>
<a href="/user/student-create">Create New Student</a>
<br>
<a href="/">Back to Home</a>
</body>
</html>

<% } %>