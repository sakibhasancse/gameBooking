package com.seu.javaFriday.Controller;


import com.seu.javaFriday.Dto.BookingRequestDTO;
import com.seu.javaFriday.Dto.GameRequest;
import com.seu.javaFriday.Dto.MessageResponse;
import com.seu.javaFriday.Entities.BookingStatus;
import com.seu.javaFriday.Entities.Game;
import com.seu.javaFriday.Entities.Role;
import com.seu.javaFriday.Entities.User;
import com.seu.javaFriday.Service.BookingService;
import com.seu.javaFriday.Service.GameService;
import com.seu.javaFriday.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    // Game Management
    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameRequest gameRequest) {
        Game game = new Game(gameRequest.getGameName(),
                gameRequest.getDescription(),
                gameRequest.getMaxPlayers(),
                gameRequest.getDurationMinutes());
        return ResponseEntity.ok(gameService.createGame(game));
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @Valid @RequestBody GameRequest gameRequest) {
        Game gameDetails = new Game(gameRequest.getGameName(),
                gameRequest.getDescription(),
                gameRequest.getMaxPlayers(),
                gameRequest.getDurationMinutes());
        return ResponseEntity.ok(gameService.updateGame(id, gameDetails));
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<MessageResponse> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok(new MessageResponse("Game deleted successfully!"));
    }

    @PutMapping("/games/{id}/toggle-status")
    public ResponseEntity<Game> toggleGameStatus(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.toggleGameStatus(id));
    }

    // User Management
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/players")
    public ResponseEntity<List<User>> getAllPlayers() {
        return ResponseEntity.ok(userService.getUsersByRole(Role.USER));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    // Booking Management
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingRequestDTO>> getAllBookingRequests() {
        return ResponseEntity.ok(bookingService.getAllBookingRequests());
    }

    @GetMapping("/bookings/pending")
    public ResponseEntity<List<BookingRequestDTO>> getPendingBookings() {
        return ResponseEntity.ok(bookingService.getBookingRequestsByStatus(BookingStatus.PENDING));
    }

    @PutMapping("/bookings/{id}/approve")
    public ResponseEntity<BookingRequestDTO> approveBooking(@PathVariable Long id,
                                                            @RequestBody(required = false) String adminNotes) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, BookingStatus.APPROVED, adminNotes));
    }

    @PutMapping("/bookings/{id}/reject")
    public ResponseEntity<BookingRequestDTO> rejectBooking(@PathVariable Long id,
                                                           @RequestBody(required = false) String adminNotes) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, BookingStatus.REJECTED, adminNotes));
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<MessageResponse> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingRequest(id);
        return ResponseEntity.ok(new MessageResponse("Booking request deleted successfully!"));
    }
}
