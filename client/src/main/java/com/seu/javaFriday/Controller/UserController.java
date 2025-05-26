package com.seu.javaFriday.Controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
            return "redirect:/login";
        }

        String token = sessionService.getToken(session);
        List<Game> activeGames = apiService.getAllActiveGames();
        List<Booking> userBookings = apiService.getUserBookings(token);

        model.addAttribute("username", sessionService.getUsername(session));
        model.addAttribute("games", activeGames);
        model.addAttribute("bookings", userBookings);

        return "user/dashboard";
    }

    @GetMapping("/games")
    public String viewGames(HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/login";
        }

        List<Game> activeGames = apiService.getAllActiveGames();
        model.addAttribute("games", activeGames);
        model.addAttribute("username", sessionService.getUsername(session));

        return "user/games";
    }

    @GetMapping("/games/{id}")
    public String viewGameDetails(@PathVariable Long id, HttpSession session, Model model) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/login";
        }

        Game game = apiService.getGameById(id);
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
                              RedirectAttributes redirectAttributes) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/login";
        }

        String token = sessionService.getToken(session);
        Booking booking = apiService.requestSlot(gameId, token);

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
            return "redirect:/login";
        }

        String token = sessionService.getToken(session);
        List<Booking> bookings = apiService.getUserBookings(token);

        model.addAttribute("bookings", bookings);
        model.addAttribute("username", sessionService.getUsername(session));

        return "user/bookings";
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public String cancelBooking(@PathVariable Long bookingId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (!sessionService.isAuthenticated(session) || !sessionService.isUser(session)) {
            return "redirect:/login";
        }

        String token = sessionService.getToken(session);
        boolean success = apiService.cancelBooking(bookingId, token);

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Booking cancelled successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel booking.");
        }

        return "redirect:/user/bookings";
    }
}
