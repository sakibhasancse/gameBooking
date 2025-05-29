<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/29/2025
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Game Details" />
<jsp:include page="../common/header.jsp" />

<h2>${game.gameName}</h2>
<p>${game.description}</p>
<p>Max Players: ${game.maxPlayers}</p>
<p>Duration: ${game.durationMinutes} minutes</p>

<h3>Request a Slot</h3>
<form action="/user/games/${game.id}/request-slot" method="post">
    <div class="mb-3">
        <label for="requestedDateTime" class="form-label">Requested Date & Time</label>
        <input type="datetime-local" class="form-control" name="requestedDateTime" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit Request</button>
</form>

<jsp:include page="../common/footer.jsp" />
