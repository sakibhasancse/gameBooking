<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/29/2025
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Your Bookings" />
<jsp:include page="../common/header.jsp" />

<h2>Your Bookings</h2>

<c:if test="${not empty success}">
    <div class="alert alert-success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<table class="table">
    <thead>
    <tr>
        <th>Game</th>
        <th>Requested Date/Time</th>
        <th>Status</th>
        <th>Request Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.game.gameName}</td>
            <td><fmt:formatDate value="${booking.requestedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>${booking.status}</td>
            <td><fmt:formatDate value="${booking.createdAt}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>
                <form action="/user/bookings/${booking.id}/cancel" method="post" style="display:inline;">
                    <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../common/footer.jsp" />
