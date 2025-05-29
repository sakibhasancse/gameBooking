package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Entities.Game;
import com.seu.javaFriday.Repo.GameRepo;
import com.seu.javaFriday.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/active")
    public ResponseEntity<List<Game>> getActiveGames() {
        return ResponseEntity.ok(gameService.getActiveGames());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchActiveGames(@RequestParam String keyword) {
        return ResponseEntity.ok(gameService.searchActiveGames(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        System.out.println("id: =====>> " + id);
        return gameService.getGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}