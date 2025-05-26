package com.seu.javaFriday.Service;


import com.seu.javaFriday.Dto.BookingRequestDTO;
import com.seu.javaFriday.Entities.BookingRequest;
import com.seu.javaFriday.Entities.BookingStatus;
import com.seu.javaFriday.Entities.Game;
import com.seu.javaFriday.Entities.User;
import com.seu.javaFriday.Repo.BookingRequestRepo;
import com.seu.javaFriday.Repo.GameRepo;
import com.seu.javaFriday.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRequestRepo bookingRequestRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private GameRepo gameRepository;

    public List<BookingRequestDTO> getAllBookingRequests() {
        return bookingRequestRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingRequestDTO> getBookingRequestsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return bookingRequestRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingRequestDTO> getBookingRequestsByStatus(BookingStatus status) {
        return bookingRequestRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingRequestDTO createBookingRequest(String username, BookingRequestDTO requestDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Game game = gameRepository.findById(requestDTO.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + requestDTO.getGameId()));

        if (!game.getIsActive()) {
            throw new RuntimeException("Game is not active");
        }

        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setUser(user);
        bookingRequest.setGame(game);
        bookingRequest.setRequestedDateTime(requestDTO.getRequestedDateTime());

        BookingRequest savedRequest = bookingRequestRepository.save(bookingRequest);
        return convertToDTO(savedRequest);
    }

    public BookingRequestDTO updateBookingStatus(Long id, BookingStatus status, String adminNotes) {
        BookingRequest request = bookingRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking request not found with id: " + id));

        request.setStatus(status);
        request.setAdminNotes(adminNotes);

        BookingRequest updatedRequest = bookingRequestRepository.save(request);
        return convertToDTO(updatedRequest);
    }

    public void deleteBookingRequest(Long id) {
        bookingRequestRepository.deleteById(id);
    }

    public Optional<BookingRequestDTO> getBookingRequestById(Long id) {
        return bookingRequestRepository.findById(id)
                .map(this::convertToDTO);
    }

    private BookingRequestDTO convertToDTO(BookingRequest request) {
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setId(request.getId());
        dto.setGameId(request.getGame().getId());
        dto.setRequestedDateTime(request.getRequestedDateTime());
        dto.setStatus(request.getStatus());
        dto.setAdminNotes(request.getAdminNotes());
        dto.setGameName(request.getGame().getGameName());
        dto.setUsername(request.getUser().getUsername());
        dto.setCreatedAt(request.getCreatedAt());
        return dto;
    }
}