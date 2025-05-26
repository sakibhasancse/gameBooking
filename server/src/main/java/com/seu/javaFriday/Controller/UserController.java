package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Dto.BookingRequestDTO;
import com.seu.javaFriday.Dto.MessageResponse;
import com.seu.javaFriday.Entities.User;
import com.seu.javaFriday.Repo.UserRepo;
import com.seu.javaFriday.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingRequestDTO>> getMyBookings(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(bookingService.getBookingRequestsByUser(username));
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingRequestDTO> createBookingRequest(
            @Valid @RequestBody BookingRequestDTO bookingRequest,
            Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(bookingService.createBookingRequest(username, bookingRequest));
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingRequestDTO> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<MessageResponse> cancelBooking(@PathVariable Long id) {
        bookingService.deleteBookingRequest(id);
        return ResponseEntity.ok(new MessageResponse("Booking cancelled successfully!"));
    }
}
