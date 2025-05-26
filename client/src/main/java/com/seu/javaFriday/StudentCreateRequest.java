package com.seu.javaFriday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private String department;
    private String registrationNumber;
//    private String dateOfBirth;
}
