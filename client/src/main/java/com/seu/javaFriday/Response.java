package com.seu.javaFriday;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String code;
    private String msg;
    private String test;
    private Object data;
    private List<Course> courses;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
