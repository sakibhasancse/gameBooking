<%--
  Created by IntelliJ IDEA.
  User: BS01409
  Date: 5/27/2025
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.json.*, java.util.*" %>
<html>
<body>
<h2>Player Booking Requests</h2>

<%
    String jsonStr = (String) request.getAttribute("requests");
    JSONArray arr = new JSONArray(jsonStr);

    for (int i = 0; i < arr.length(); i++) {
        JSONObject reqObj = arr.getJSONObject(i);
%>
<div>
    <b>Player:</b> <%= reqObj.getString("playerName") %><br/>
    <b>Status:</b> <%= reqObj.getString("status") %><br/>
    <b>Game:</b> <%= reqObj.getJSONObject("game").getString("name") %><br/>

    <% if (reqObj.getString("status").equals("PENDING")) { %>
    <form method="post" action="approverequest">
        <input type="hidden" name="requestId" value="<%= reqObj.getInt("id") %>" />
        <input type="submit" value="Approve" />
    </form>
    <% } %>
</div>
<hr/>
<% } %>
</body>
</html>
