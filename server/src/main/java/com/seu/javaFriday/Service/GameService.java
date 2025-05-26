package com.seu.javaFriday.Service;

import com.seu.javaFriday.Entities.Game;
import com.seu.javaFriday.Repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepo gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getActiveGames() {
        return gameRepository.findByIsActiveTrue();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public List<Game> searchGames(String keyword) {
        return gameRepository.findByGameNameContainingIgnoreCase(keyword);
    }

    public List<Game> searchActiveGames(String keyword) {
        return gameRepository.findByIsActiveTrueAndGameNameContainingIgnoreCase(keyword);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));

        game.setGameName(gameDetails.getGameName());
        game.setDescription(gameDetails.getDescription());
        game.setMaxPlayers(gameDetails.getMaxPlayers());
        game.setDurationMinutes(gameDetails.getDurationMinutes());
        game.setIsActive(gameDetails.getIsActive());

        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public Game toggleGameStatus(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));

        game.setIsActive(!game.getIsActive());
        return gameRepository.save(game);
    }
}