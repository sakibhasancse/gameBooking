package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Service.ApiService;
import com.seu.javaFriday.dto.BookingRequestDto;
import com.seu.javaFriday.dto.GameDto;
import com.seu.javaFriday.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        List<GameDto> games = apiService.getAllGames();
        List<UserDto> users = apiService.getAllUsers();
        List<BookingRequestDto> bookingRequests = apiService.getAllBookingRequests();

        model.addAttribute("gamesCount", games.size());
        model.addAttribute("usersCount", users.size());
        model.addAttribute("bookingRequestsCount", bookingRequests.size());

        return "admin/dashboard";
    }

    // Game Management
    @GetMapping("/games")
    public String manageGames(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        List<GameDto> games = apiService.getAllGames();
        model.addAttribute("games", games);
        return "admin/games";
    }

    @GetMapping("/games/add")
    public String addGameForm(HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }
        return "admin/add-game";
    }

    @PostMapping("/games/add")
    public String addGame(@ModelAttribute GameDto gameDto,
                          RedirectAttributes redirectAttributes,
                          HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        GameDto result = apiService.createGame(gameDto);
        if (result != null) {
            redirectAttributes.addFlashAttribute("success", "Game added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to add game");
        }
        return "redirect:/admin/games";
    }

    @GetMapping("/games/edit/{id}")
    public String editGameForm(@PathVariable Long id, Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        GameDto game = apiService.getGameById(id);
        model.addAttribute("game", game);
        return "admin/edit-game";
    }

    @PostMapping("/games/edit/{id}")
    public String editGame(@PathVariable Long id,
                           @ModelAttribute GameDto gameDto,
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        GameDto result = apiService.updateGame(id, gameDto);
        if (result != null) {
            redirectAttributes.addFlashAttribute("success", "Game updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update game");
        }
        return "redirect:/admin/games";
    }

    @PostMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable Long id,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        boolean result = apiService.deleteGame(id);
        if (result) {
            redirectAttributes.addFlashAttribute("success", "Game deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to delete game");
        }
        return "redirect:/admin/games";
    }

    // User Management
    @GetMapping("/users")
    public String manageUsers(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        List<UserDto> users = apiService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        boolean result = apiService.deleteUser(id);
        if (result) {
            redirectAttributes.addFlashAttribute("success", "User deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to delete user");
        }
        return "redirect:/admin/users";
    }

    // Booking Management
    @GetMapping("/bookings")
    public String manageBookings(Model model, HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        List<BookingRequestDto> bookingRequests = apiService.getAllBookingRequests();
        model.addAttribute("bookingRequests", bookingRequests);
        return "admin/bookings";
    }

    @PostMapping("/bookings/{id}/status")
    public String updateBookingStatus(@PathVariable Long id,
                                      @RequestParam String status,
                                      @RequestParam(required = false) String adminNotes,
                                      RedirectAttributes redirectAttributes,
                                      HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return "redirect:/games";
        }

        BookingRequestDto result = apiService.updateBookingRequestStatus(id, status, adminNotes);
        if (result != null) {
            redirectAttributes.addFlashAttribute("success", "Booking status updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update booking status");
        }
        return "redirect:/admin/bookings";
    }
}