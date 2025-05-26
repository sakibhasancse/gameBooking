package com.seu.javaFriday.Model;


public class Booking {
    private Long id;
    private Long gameId;
    private Long userId;
    private LocalDateTime bookingTime;
    private String status;
    private Game game;
    private User user;

    // Constructors
    public Booking() {}

    public Booking(Long gameId, Long userId, String status) {
        this.gameId = gameId;
        this.userId = userId;
        this.status = status;
        this.bookingTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

