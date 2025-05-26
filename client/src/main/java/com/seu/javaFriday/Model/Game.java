package com.seu.javaFriday.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;
    private String name;
    private String description;
    private int maxPlayers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private int availableSlots;
}
