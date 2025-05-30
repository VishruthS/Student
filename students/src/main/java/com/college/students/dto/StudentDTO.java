package com.college.students.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private int day;
    private int month;
    private int year;
    private int age;
}
