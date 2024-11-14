package com.example.task_tracker_system.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskTrackerSystem {
    private String ID;
    private String title;
    private String description;
    private String status;
}
