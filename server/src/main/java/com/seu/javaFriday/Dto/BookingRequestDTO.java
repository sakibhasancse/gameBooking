package com.seu.javaFriday.Dto;

import com.seu.javaFriday.Entities.BookingStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BookingRequestDTO {
    private Long id;

    @NotNull
    private Long gameId;

    @NotNull
    private LocalDateTime requestedDateTime;

    private BookingStatus status;
    private String adminNotes;
    private String gameName;
    private String username;
    private LocalDateTime createdAt;

    public BookingRequestDTO() {}

    public BookingRequestDTO(Long gameId, LocalDateTime requestedDateTime) {
        this.gameId = gameId;
        this.requestedDateTime = requestedDateTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public LocalDateTime getRequestedDateTime() { return requestedDateTime; }
    public void setRequestedDateTime(LocalDateTime requestedDateTime) { this.requestedDateTime = requestedDateTime; }

    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }

    public String getAdminNotes() { return adminNotes; }
    public void setAdminNotes(String adminNotes) { this.adminNotes = adminNotes; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}


