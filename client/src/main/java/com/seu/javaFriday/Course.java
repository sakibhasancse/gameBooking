package com.seu.javaFriday;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String courseName;
    private String courseCode;
    private int section;

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
