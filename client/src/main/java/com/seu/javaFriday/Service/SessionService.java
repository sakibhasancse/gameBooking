package com.seu.javaFriday.Service;

@Service
public class SessionService {

    private static final String TOKEN_KEY = "auth_token";
    private static final String USERNAME_KEY = "username";
    private static final String ROLE_KEY = "role";

    public void setAuthSession(HttpSession session, String token, String username, String role) {
        session.setAttribute(TOKEN_KEY, token);
        session.setAttribute(USERNAME_KEY, username);
        session.setAttribute(ROLE_KEY, role);
    }

    public String getToken(HttpSession session) {
        return (String) session.getAttribute(TOKEN_KEY);
    }

    public String getUsername(HttpSession session) {
        return (String) session.getAttribute(USERNAME_KEY);
    }

    public String getRole(HttpSession session) {
        return (String) session.getAttribute(ROLE_KEY);
    }

    public boolean isAuthenticated(HttpSession session) {
        return getToken(session) != null;
    }

    public boolean isAdmin(HttpSession session) {
        return "ADMIN".equals(getRole(session));
    }

    public boolean isUser(HttpSession session) {
        return "USER".equals(getRole(session));
    }

    public void clearSession(HttpSession session) {
        session.removeAttribute(TOKEN_KEY);
        session.removeAttribute(USERNAME_KEY);
        session.removeAttribute(ROLE_KEY);
    }
}