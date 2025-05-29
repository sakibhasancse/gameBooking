package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Service.TokenService;
import com.seu.javaFriday.dto.BookingRequestDto;
import com.seu.javaFriday.dto.GameDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.seu.javaFriday.Model.Booking;
import com.seu.javaFriday.Model.Game;
import com.seu.javaFriday.Service.ApiService;
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
    private TokenService tokenService;

    @GetMapping("/dashboard")
    public String userDashboard(HttpServletRequest request, Model model) {
        System.out.println("userDashboard======>");
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        List<GameDto> activeGames = apiService.getAllActiveGames();
        List<BookingRequestDto> userBookings = apiService.getUserBookings();
        System.out.println("userBookings======>" + activeGames);
        model.addAttribute("username", tokenService.getUsername(request));
        model.addAttribute("games", activeGames);
        model.addAttribute("bookings", userBookings);

        return "user/dashboard";
    }

    @GetMapping("/games")
    public String viewGames(HttpServletRequest request, Model model) {
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        List<GameDto> activeGames = apiService.getAllActiveGames();

        model.addAttribute("games", activeGames);
        model.addAttribute("username", tokenService.getUsername(request));

        return "user/games";
    }

    @GetMapping("/games/{id}")
    public String viewGameDetails(@PathVariable Long id, HttpServletRequest request, Model model) {
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        GameDto game = apiService.getGameById(id);
        if (game == null) {
            return "redirect:/404";
        }

        model.addAttribute("game", game);
        model.addAttribute("username", tokenService.getUsername(request));

        return "user/game-details";
    }

    @PostMapping("/games/{gameId}/request-slot")
    public String requestSlot(@PathVariable Long gameId,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("requestedDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime requestedDateTime) {
        System.out.println("tokenService.isUser(request)======>" + tokenService.isUser(request));
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        System.out.println("requestedDateTime======>");
        ResponseEntity<String> booking = apiService.requestSlot(gameId, requestedDateTime);
        System.out.println("booking======>" + booking);
        if (booking != null) {
            redirectAttributes.addFlashAttribute("success", "Slot requested successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to request slot. Game may be full.");
        }

        return "redirect:/user/dashboard";
    }

    @GetMapping("/bookings")
    public String viewBookings(HttpServletRequest request, Model model) {
        String token = tokenService.getToken(request);
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        List<BookingRequestDto> bookings = apiService.getUserBookings();

        model.addAttribute("bookings", bookings);
        model.addAttribute("username", tokenService.getUsername(request));

        return "user/bookings";
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public String cancelBooking(@PathVariable Long bookingId,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes) {
        String token = tokenService.getToken(request);
        if (!tokenService.isAuthenticated(request) || !tokenService.isUser(request)) {
            return "redirect:/auth/login";
        }

        boolean success = apiService.cancelBooking(bookingId).hasBody();

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Booking cancelled successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel booking.");
        }

        return "redirect:/user/bookings";
    }
}
