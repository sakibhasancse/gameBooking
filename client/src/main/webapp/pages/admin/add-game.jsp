<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Add Game" />
<jsp:include page="../common/header.jsp" />

<div class="row justify-content-center">
  <div class="col-md-8">
    <div class="card">
      <div class="card-header">
        <h4>Add New Game</h4>
      </div>
      <div class="card-body">
        <form action="/admin/games/add" method="post">
          <div class="mb-3">
            <label for="gameName" class="form-label">Game Name</label>
            <input type="text" class="form-control" id="gameName" name="gameName" required>
          </div>
          <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
          </div>
          <div class="mb-3">
            <label for="maxPlayers" class="form-label">Maximum Players</label>
            <input type="number" class="form-control" id="maxPlayers" name="maxPlayers" min="1" required>
          </div>
          <div class="mb-3">
            <label for="durationMinutes" class="form-label">Duration (minutes)</label>
            <input type="number" class="form-control" id="durationMinutes" name="durationMinutes" min="1">
          </div>
          <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="isActive" name="isActive" checked>
            <label class="form-check-label" for="isActive">Active</label>
          </div>
          <button type="submit" class="btn btn-primary">Add Game</button>
          <a href="/admin/games" class="btn btn-secondary">Cancel</a>
        </form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../common/footer.jsp" />