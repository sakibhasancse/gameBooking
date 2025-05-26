package com.seu.javaFriday.Repo;


import com.seu.javaFriday.Entities.BookingRequest;
import com.seu.javaFriday.Entities.BookingStatus;
import com.seu.javaFriday.Entities.Game;
import com.seu.javaFriday.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRequestRepo extends JpaRepository<BookingRequest, Long> {
    List<BookingRequest> findByUser(User user);
    List<BookingRequest> findByGame(Game game);
    List<BookingRequest> findByStatus(BookingStatus status);
    List<BookingRequest> findByUserOrderByCreatedAtDesc(User user);
    List<BookingRequest> findByGameOrderByCreatedAtDesc(Game game);
    List<BookingRequest> findAllByOrderByCreatedAtDesc();
}
