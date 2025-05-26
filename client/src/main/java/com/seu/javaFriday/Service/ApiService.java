package com.seu.javaFriday.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {

    @Value("${backend.api.url}")
    private String backendUrl;

    private final RestTemplate restTemplate;

    public ApiService() {
        this.restTemplate = new RestTemplate();
    }

    // Authentication Methods
    public UserDto login(LoginRequest loginRequest) {
        try {
            ResponseEntity<UserDto> response = restTemplate.postForEntity(
                    backendUrl + "/auth/login",
                    loginRequest,
                    UserDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public UserDto register(UserDto userDto) {
        try {
            ResponseEntity<UserDto> response = restTemplate.postForEntity(
                    backendUrl + "/auth/register",
                    userDto,
                    UserDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // Game Methods
    public List<GameDto> getAllActiveGames() {
        try {
            ResponseEntity<GameDto[]> response = restTemplate.getForEntity(
                    backendUrl + "/games/active",
                    GameDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    public List<GameDto> getAllGames() {
        try {
            ResponseEntity<GameDto[]> response = restTemplate.getForEntity(
                    backendUrl + "/games",
                    GameDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    public GameDto getGameById(Long id) {
        try {
            ResponseEntity<GameDto> response = restTemplate.getForEntity(
                    backendUrl + "/games/" + id,
                    GameDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public GameDto createGame(GameDto gameDto) {
        try {
            ResponseEntity<GameDto> response = restTemplate.postForEntity(
                    backendUrl + "/games",
                    gameDto,
                    GameDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public GameDto updateGame(Long id, GameDto gameDto) {
        try {
            HttpEntity<GameDto> request = new HttpEntity<>(gameDto);
            ResponseEntity<GameDto> response = restTemplate.exchange(
                    backendUrl + "/games/" + id,
                    HttpMethod.PUT,
                    request,
                    GameDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteGame(Long id) {
        try {
            restTemplate.delete(backendUrl + "/games/" + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // User Methods
    public List<UserDto> getAllUsers() {
        try {
            ResponseEntity<UserDto[]> response = restTemplate.getForEntity(
                    backendUrl + "/users",
                    UserDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    public UserDto getUserById(Long id) {
        try {
            ResponseEntity<UserDto> response = restTemplate.getForEntity(
                    backendUrl + "/users/" + id,
                    UserDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        try {
            restTemplate.delete(backendUrl + "/users/" + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Booking Request Methods
    public BookingRequestDto createBookingRequest(BookingRequestDto bookingRequestDto) {
        try {
            ResponseEntity<BookingRequestDto> response = restTemplate.postForEntity(
                    backendUrl + "/booking-requests",
                    bookingRequestDto,
                    BookingRequestDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public List<BookingRequestDto> getAllBookingRequests() {
        try {
            ResponseEntity<BookingRequestDto[]> response = restTemplate.getForEntity(
                    backendUrl + "/booking-requests",
                    BookingRequestDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    public List<BookingRequestDto> getBookingRequestsByUser(Long userId) {
        try {
            ResponseEntity<BookingRequestDto[]> response = restTemplate.getForEntity(
                    backendUrl + "/booking-requests/user/" + userId,
                    BookingRequestDto[].class
            );
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return Arrays.asList();
        }
    }

    public BookingRequestDto updateBookingRequestStatus(Long id, String status, String adminNotes) {
        try {
            BookingRequestDto request = new BookingRequestDto();
            request.setStatus(status);
            request.setAdminNotes(adminNotes);

            HttpEntity<BookingRequestDto> httpEntity = new HttpEntity<>(request);
            ResponseEntity<BookingRequestDto> response = restTemplate.exchange(
                    backendUrl + "/booking-requests/" + id + "/status",
                    HttpMethod.PUT,
                    httpEntity,
                    BookingRequestDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}