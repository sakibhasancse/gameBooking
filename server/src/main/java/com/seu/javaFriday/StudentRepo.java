package com.seu.javaFriday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByRegistrationNumber(String registrationNumber);
    Student findByUser(User user);
}
