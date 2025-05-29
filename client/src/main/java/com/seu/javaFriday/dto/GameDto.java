package com.seu.javaFriday.dto;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class GameDto {
    private Long id;
    private String gameName;
    private String description;
    private Integer maxPlayers;
    private Integer durationMinutes;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private List<BookingRequestDto> bookingRequests;

    // Constructors
    public GameDto() {}

    public GameDto(String gameName, String description, Integer maxPlayers, Integer durationMinutes) {
        this.gameName = gameName;
        this.description = description;
        this.maxPlayers = maxPlayers;
        this.durationMinutes = durationMinutes;
        this.isActive = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxPlayers() { return maxPlayers; }
    public void setMaxPlayers(Integer maxPlayers) { this.maxPlayers = maxPlayers; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<BookingRequestDto> getBookingRequests() { return bookingRequests; }
    public void setBookingRequests(List<BookingRequestDto> bookingRequests) { this.bookingRequests = bookingRequests; }

    public Date getCreatedAtDate() {
        if (createdAt == null) {
            return null;  // avoid NPE
        }
        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
