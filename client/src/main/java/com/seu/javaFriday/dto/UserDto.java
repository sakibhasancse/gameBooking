package com.seu.javaFriday.dto;


import java.time.LocalDateTime;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private List<BookingRequestDto> bookingRequests;

    // Constructors
    public UserDto() {}

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<BookingRequestDto> getBookingRequests() { return bookingRequests; }
    public void setBookingRequests(List<BookingRequestDto> bookingRequests) { this.bookingRequests = bookingRequests; }
}