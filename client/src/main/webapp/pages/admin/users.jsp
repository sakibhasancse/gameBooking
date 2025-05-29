<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Manage Users" />
<jsp:include page="../common/header.jsp" />

<h2>Manage Users</h2>

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
      <th>Username</th>
      <th>Email</th>
      <th>Role</th>
      <th>Created At</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>
                        <span class="badge ${user.role == 'ADMIN' ? 'bg-danger' : 'bg-primary'}">
                            ${user.role}
                        </span>
        </td>
        <td><fmt:formatDate value="${user.createdAtDate}" pattern="yyyy-MM-dd HH:mm" /></td>
        <td>
          <c:if test="${user.role != 'ADMIN'}">
            <form action="/admin/users/delete/${user.id}" method="post" style="display: inline-block;"
                  onsubmit="return confirm('Are you sure you want to delete this user?')">
              <button type="submit" class="btn btn-sm btn-danger">Delete</button>
            </form>
          </c:if>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="../common/footer.jsp" />