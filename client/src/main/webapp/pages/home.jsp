<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 3/21/2025
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.seu.javaFriday.User, java.util.List, com.seu.javaFriday.Course" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<%
    Boolean isLogin = (Boolean) session.getAttribute("login");
    if (isLogin == null || !isLogin) {
%>
<h2>You are not logged in!</h2>
<a href="login.jsp">Login</a>
<%
} else {
    User user = (User) session.getAttribute("user");
    List<Course> courseList = (List<Course>) session.getAttribute("course");
%>

<h1>Welcome, <%= user.getUsername() %></h1>

<h2>Course List</h2>
<table border="1">
    <tr>
        <th>Course Code</th>
        <th>Course Name</th>
        <th>Section</th>
    </tr>
    <%
        for (Course course : courseList) {
    %>
    <tr>
        <td><%= course.getCourseCode() %></td>
        <td><%= course.getCourseName() %></td>
        <td><%= course.getSection() %></td>
    </tr>
    <%
        }
    %>
</table>

<a href="logout">Logout</a>

<%
    }
%>

</body>
</html>
