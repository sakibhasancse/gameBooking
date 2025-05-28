package com.seu.javaFriday.Controller;

import com.seu.javaFriday.dto.BookingRequestDto;
import com.seu.javaFriday.dto.GameDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.seu.javaFriday.Model.Booking;
import com.seu.javaFriday.Model.Game;
import com.seu.javaFriday.Service.ApiService;
import com.seu.javaFriday.Service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

        String token = sessionService.getToken(session);
        List<GameDto> activeGames = apiService.getAllActiveGames();
        List<BookingRequestDto> userBookings = apiService.getUserBookings();

        model.addAttribute("username", sessionService.getUsername(session));
        model.addAttribute("games", activeGames);
        model.addAttribute("bookings", userBookings);

        return "user/dashboard";
    }

    @GetMapping("/games")
    public String viewGames(HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

        List<GameDto> activeGames = apiService.getAllActiveGames();
        model.addAttribute("games", activeGames);
        model.addAttribute("username", sessionService.getUsername(session));

        return "user/games";
    }

    @GetMapping("/games/{id}")
    public String viewGameDetails(@PathVariable Long id, HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

        GameDto game = apiService.getGameById(id);
        if (game == null) {
            return "redirect:/user/games";
        }

        model.addAttribute("game", game);
        model.addAttribute("username", sessionService.getUsername(session));

        return "user/game-details";
    }

    @PostMapping("/games/{gameId}/request-slot")
    public String requestSlot(@PathVariable Long gameId,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("requestedDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime requestedDateTime) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

//        String token = sessionService.getToken(session);
        ResponseEntity<String> booking = apiService.requestSlot(gameId,  requestedDateTime);

        if (booking != null) {
            redirectAttributes.addFlashAttribute("success", "Slot requested successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to request slot. Game may be full.");
        }

        return "redirect:/user/games/" + gameId;
    }

    @GetMapping("/bookings")
    public String viewBookings(HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

        String token = sessionService.getToken(session);
        List<BookingRequestDto> bookings = apiService.getUserBookings();

        model.addAttribute("bookings", bookings);
        model.addAttribute("username", sessionService.getUsername(session));

        return "user/bookings";
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public String cancelBooking(@PathVariable Long bookingId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/auth/login";
        }

//        String token = sessionService.getToken(session);
        boolean success = apiService.cancelBooking(bookingId).hasBody();

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Booking cancelled successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel booking.");
        }

        return "redirect:/user/bookings";
    }
}
