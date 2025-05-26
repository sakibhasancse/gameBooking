<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>

<!-- src/main/webapp/WEB-INF/jsp/admin/games.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Manage Games" />
<jsp:include page="../common/header.jsp" />

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Manage Games</h2>
    <a href="/admin/games/add" class="btn btn-primary">Add New Game</a>
</div>

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
            <th>Game Name</th>
            <th>Description</th>
            <th>Max Players</th>
            <th>Duration (min)</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="game" items="${games}">
            <tr>
                <td>${game.id}</td>
                <td>${game.gameName}</td>
                <td>${game.description}</td>
                <td>${game.maxPlayers}</td>
                <td>${game.durationMinutes}</td>
                <td>
                        <span class="badge ${game.isActive ? 'bg-success' : 'bg-danger'}">
                                ${game.isActive ? 'Active' : 'Inactive'}
                        </span>
                </td>
                <td><fmt:formatDate value="${game.createdAt}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>
                    <a href="/admin/games/edit/${game.id}" class="btn btn-sm btn-warning">Edit</a>
                    <form action="/admin/games/delete/${game.id}" method="post" style="display: inline-block;"
                          onsubmit="return confirm('Are you sure you want to delete this game?')">
                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../common/footer.jsp" />