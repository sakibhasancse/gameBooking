package com.seu.javaFriday.Repo;

import com.seu.javaFriday.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByIsActiveTrue();
    List<Game> findByGameNameContainingIgnoreCase(String gameName);
    List<Game> findByIsActiveTrueAndGameNameContainingIgnoreCase(String gameName);
}