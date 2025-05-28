package com.seu.javaFriday.dto;


import java.time.LocalDateTime;

public class BookingRequestDto {
    private Long id;
    private UserDto user;
    private GameDto game;
    private LocalDateTime requestedDateTime;
    private String status;
    private LocalDateTime createdAt;
    private String adminNotes;

    // Constructors
    public BookingRequestDto() {
    }

    public BookingRequestDto(UserDto user, GameDto game, LocalDateTime requestedDateTime) {
        this.user = user;
        this.game = game;
        this.requestedDateTime = requestedDateTime;
        this.status = "PENDING";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public LocalDateTime getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(LocalDateTime requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAdminNotes() {
        return adminNotes;
    }

    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }
}
