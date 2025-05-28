package com.seu.javaFriday.Controller;


import com.seu.javaFriday.Service.ApiService;
import com.seu.javaFriday.dto.BookingRequestDto;
import com.seu.javaFriday.dto.GameDto;
import com.seu.javaFriday.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public String gamesList(Model model, HttpSession session) {
        List<GameDto> games = apiService.getAllActiveGames();
        model.addAttribute("games", games);

        UserDto user = (UserDto) session.getAttribute("user");
        if (user != null) {
            List<BookingRequestDto> userBookings = apiService.getBookingRequestsByUser(user.getId());
            model.addAttribute("userBookings", userBookings);
        }

        return "games/list";
    }

    @GetMapping("/{id}")
    public String gameDetails(@PathVariable Long id, Model model) {
        GameDto game = apiService.getGameById(id);
        model.addAttribute("game", game);
        return "games/details";
    }

    @PostMapping("/{id}/book")
    public String bookGame(@PathVariable Long id,
                           @RequestParam String requestedDateTime,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        GameDto game = apiService.getGameById(id);
        if (game == null) {
            redirectAttributes.addFlashAttribute("error", "Game not found");
            return "redirect:/games";
        }

        BookingRequestDto bookingRequest = new BookingRequestDto();
        bookingRequest.setUser(user);
        bookingRequest.setGame(game);
        bookingRequest.setRequestedDateTime(LocalDateTime.parse(requestedDateTime));

        BookingRequestDto result = apiService.createBookingRequest(bookingRequest);

        if (result != null) {
            redirectAttributes.addFlashAttribute("success", "Booking request submitted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to submit booking request");
        }

        return "redirect:/games";
    }
}
