<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Game Booking System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Game Booking System</a>
        <div class="navbar-nav ms-auto">
            <c:if test="${sessionScope.isLoggedIn}">
                <span class="navbar-text me-3">Welcome, ${sessionScope.user.username}!</span>
                <c:if test="${sessionScope.isAdmin}">
                    <a class="nav-link" href="/admin/dashboard">Admin Dashboard</a>
                </c:if>
                <c:if test="${!sessionScope.isAdmin}">
                    <a class="nav-link" href="/games">Games</a>
                </c:if>
                <a class="nav-link" href="/auth/logout">Logout</a>
            </c:if>
            <c:if test="${!sessionScope.isLoggedIn}">
                <a class="nav-link" href="/auth/login">Login</a>
                <a class="nav-link" href="/auth/register">Register</a>
            </c:if>
        </div>
    </div>
</nav>
<div class="container mt-4">

    <!-- src/main/webapp/WEB-INF/jsp/common/footer.jsp -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
