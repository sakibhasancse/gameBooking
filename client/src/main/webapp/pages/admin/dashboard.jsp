<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- src/main/webapp/WEB-INF/jsp/admin/dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Admin Dashboard" />
<jsp:include page="../common/header.jsp" />

<div class="row">
    <div class="col-md-12">
        <h2>Admin Dashboard</h2>
    </div>
</div>

<div class="row mb-4">
    <div class="col-md-4">
        <div class="card bg-primary text-white">
            <div class="card-body">
                <h5 class="card-title">Total Games</h5>
                <h2>${gamesCount}</h2>
                <a href="/admin/games" class="btn btn-light">Manage Games</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card bg-success text-white">
            <div class="card-body">
                <h5 class="card-title">Total Users</h5>
                <h2>${usersCount}</h2>
                <a href="/admin/users" class="btn btn-light">Manage Users</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card bg-warning text-white">
            <div class="card-body">
                <h5 class="card-title">Booking Requests</h5>
                <h2>${bookingRequestsCount}</h2>
                <a href="/admin/bookings" class="btn btn-light">Manage Bookings</a>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <h4>Quick Actions</h4>
        <a href="/admin/games/add" class="btn btn-primary me-2">Add New Game</a>
        <a href="/admin/bookings" class="btn btn-warning me-2">View Pending Requests</a>
        <a href="/games" class="btn btn-info">View Site as User</a>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />