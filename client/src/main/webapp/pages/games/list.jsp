<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- src/main/webapp/WEB-INF/jsp/games/list.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Games" />
<jsp:include page="../common/header.jsp" />

<h2>Available Games</h2>

<c:if test="${not empty success}">
  <div class="alert alert-success">${success}</div>
</c:if>
<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<div class="row">
  <c:forEach var="game" items="${games}">
    <div class="col-md-4 mb-4">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">${game.gameName}</h5>
          <p class="card-text">${game.description}</p>
          <p class="card-text">
            <small class="text-muted">
              Max Players: ${game.maxPlayers} |
              Duration: ${game.durationMinutes} minutes
            </small>
          </p>

          <c:if test="${sessionScope.isLoggedIn and !sessionScope.isAdmin}">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#bookModal${game.id}">
              Book Now
            </button>

            <!-- Modal -->
            <div class="modal fade" id="bookModal${game.id}" tabindex="-1">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">Book ${game.gameName}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                  </div>
                  <form action="/games/${game.id}/book" method="post">
                    <div class="modal-body">
                      <div class="mb-3">
                        <label for="requestedDateTime${game.id}" class="form-label">Preferred Date & Time</label>
                        <input type="datetime-local" class="form-control" id="requestedDateTime${game.id}" name="requestedDateTime" required>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                      <button type="submit" class="btn btn-primary">Submit Request</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </c:if>

          <c:if test="${!sessionScope.isLoggedIn}">
            <a href="/auth/login" class="btn btn-primary">Login to Book</a>
          </c:if>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

<c:if test="${sessionScope.isLoggedIn and !sessionScope.isAdmin and not empty userBookings}">
  <h3 class="mt-5">Your Booking Requests</h3>
  <div class="table-responsive">
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Game</th>
        <th>Requested Date/Time</th>
        <th>Status</th>
        <th>Admin Notes</th>
        <th>Request Date</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="booking" items="${userBookings}">
        <tr>
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
          <td>${booking.adminNotes}</td>
          <td><fmt:formatDate value="${booking.createdAt}" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</c:if>

<jsp:include page="../common/footer.jsp" />