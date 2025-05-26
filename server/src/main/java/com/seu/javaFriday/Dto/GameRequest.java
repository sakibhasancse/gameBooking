package com.seu.javaFriday.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GameRequest {
    @NotBlank
    private String gameName;

    private String description;

    @NotNull
    @Positive
    private Integer maxPlayers;

    @Positive
    private Integer durationMinutes;

    public GameRequest() {}

    public GameRequest(String gameName, String description, Integer maxPlayers, Integer durationMinutes) {
        this.gameName = gameName;
        this.description = description;
        this.maxPlayers = maxPlayers;
        this.durationMinutes = durationMinutes;
    }

    // Getters and Setters
    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxPlayers() { return maxPlayers; }
    public void setMaxPlayers(Integer maxPlayers) { this.maxPlayers = maxPlayers; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
