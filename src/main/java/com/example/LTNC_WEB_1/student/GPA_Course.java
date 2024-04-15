package com.example.LTNC_WEB_1.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GPA_Course {
    private String courseName;
    private Integer credits;
    private Double Gpa;
}
