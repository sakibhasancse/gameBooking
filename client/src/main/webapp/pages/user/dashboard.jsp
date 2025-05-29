<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/29/2025
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="User Dashboard" />
<jsp:include page="../common/header.jsp" />

<h2>Welcome, ${username}!</h2>

<h3>Your Active Bookings</h3>
<c:if test="${empty bookings}">
    <p>No bookings yet.</p>
</c:if>
<c:forEach var="booking" items="${bookings}">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">${booking.game.gameName}</h5>
            <p class="card-text">Status: ${booking.status}</p>
            <p>Requested at: ${booking.requestedDateTime}</p>
        </div>
    </div>
</c:forEach>

<h3 class="mt-5">Available Games</h3>
<c:forEach var="game" items="${games}">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">${game.gameName}</h5>
            <p>${game.description}</p>
            <a href="/user/games/${game.id}" class="btn btn-primary">View Details</a>
        </div>
    </div>
</c:forEach>

<jsp:include page="../common/footer.jsp" />
