package com.seu.javaFriday;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String courseName;
    private String courseCode;
    private int section;

    public Course(String courseName, String courseCode, int section) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.section = section;
    }
}
