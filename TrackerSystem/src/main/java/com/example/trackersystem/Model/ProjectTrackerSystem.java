package com.example.trackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectTrackerSystem {
    private int ID;
    private String title;
    private String description;
    private boolean status;
    private String companyName;
}
