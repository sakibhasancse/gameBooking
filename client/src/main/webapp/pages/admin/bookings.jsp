<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- src/main/webapp/WEB-INF/jsp/admin/bookings.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Manage Bookings" />
<jsp:include page="../common/header.jsp" />

<h2>Manage Booking Requests</h2>

<c:if test="${not empty success}">
    <div class="alert alert-success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Game</th>
            <th>Requested Date/Time</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Admin Notes</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${bookingRequests}">
        <tr>
            <td>${booking.id}</td>
            <td>${booking.user.username}</td>
            <td>${booking.game.gameName}</td>
            <td><fmt:formatDate value="${booking.requestedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>
                        <span class="badge
                            <c:choose>
                                <c:when test='${booking.status == "APPROVED"}'>bg-success</c:when>
                                <c:when test='${booking.status == "REJECTED"}'>bg-danger</c:when>
                                <c:otherwise>bg-warning</c:otherwise>
                            </c:choose>
                        ">${booking.status}</span>
            </td>
            <td><fmt:formatDate value="${booking.createdAt}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>${booking.adminNotes}</td>
            <td>
                <c:if test='${booking.status == "PENDING"}'>
                <button type="button" class="btn btn-sm btn-success me-1" data-bs-toggle="modal" data-bs-target="#approveModal${booking.id}">
                    Approve
                </button>
                <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#rejectModal${booking.id}">
                    Reject
                </button>

                <!-- Approve Modal -->
                <div class="modal fade" id="approveModal${booking.id}" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Approve Booking Request</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <form action="/admin/bookings/${booking.id}/status" method="post">
                                <div class="modal-body">
                                    <input type="hidden" name="status" value="APPROVED">
                                    <div class="mb-3">
                                        <label for="adminNotes${booking.id}" class="form-label">Admin Notes (Optional)</label>
                                        <textarea class="form-control" id="adminNotes${booking.id}" name="adminNotes" rows="3"></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-success">Approve</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Reject Modal -->
                <div class="modal fade" id="rejectModal${booking.id}" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Reject Booking Request</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <form action="/admin/bookings/${booking.id}/status" method="post">
                                <div class="modal-body">
                                    <input type="hidden" name="status" value="REJECTED">
                                    <div class="mb-3">
                                        <label for="rejectNotes${booking.id}" class="form-label">Reason for Rejection</label>
                                        <textarea class="form-control" id="rejectNotes${booking.id}" name="adminNotes" rows="3" required></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-danger">Reject</button>
                                </div>
