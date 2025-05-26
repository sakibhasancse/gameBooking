package com.seu.javaFriday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private String department;
    private String registrationNumber;
}
