package com.example.student.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSystem {
    private int ID;
    private String name;
    private int age;
    private String degree;
    private double GPA;
}
