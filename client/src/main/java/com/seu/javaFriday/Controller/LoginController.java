package com.seu.javaFriday.Controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@WebServlet("/login")
    public class LoginController extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Create base64-encoded Basic Auth header
        String credentials = username + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
        String authHeader = "Basic " + encoded;

        // Test auth by calling a secured endpoint
        URL url = new URL("http://localhost:8080/games"); // secured endpoint
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Basic " + encoded);

        int code = conn.getResponseCode();

        if (code == 200) {
            // Save session
            HttpSession session = req.getSession();
            session.setAttribute("authHeader", authHeader);
            session.setAttribute("username", username);

            // Check if admin
            // You can also call a "/me" endpoint to return the role
            if (username.equals("admin")) {
                session.setAttribute("role", "ADMIN");
                resp.sendRedirect("admin.jsp");
            } else {
                session.setAttribute("role", "USER");
                resp.sendRedirect("games");
            }
        } else {
            resp.getWriter().println("Login failed.");
        }
    }
}
