package com.example.task_tracker_system.Controller;

import com.example.task_tracker_system.ApiResponse.ApiResponse;
import com.example.task_tracker_system.Model.TaskTrackerSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task-tracker-system")
public class TaskTrackerSystemController {

    ArrayList<TaskTrackerSystem> taskTrackerSystem = new ArrayList<>();



    @GetMapping("/get")
    public ArrayList<TaskTrackerSystem> displayTasks(){
        return taskTrackerSystem;
    }

    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody TaskTrackerSystem task){
        taskTrackerSystem.add(task);
        return new ApiResponse("Task created successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@RequestBody TaskTrackerSystem task,@PathVariable int index){
        taskTrackerSystem.set(index, task);
        return new ApiResponse("Task updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        taskTrackerSystem.remove(index);
        return new ApiResponse("Task deleted successfully");
    }

    @PutMapping("/change-status/{status}/{index}")
    public ApiResponse changeStatus(@PathVariable String status,@PathVariable int index){
        if (!(status.equals("done")||status.equals("not done"))){
            return new ApiResponse("status can only be done or not done");
        }

        taskTrackerSystem.get(index).setStatus(status);
        return new ApiResponse("Task status changed successfully");
    }

    @GetMapping("/search-by-title/{title}")
    public TaskTrackerSystem getTaskByTitle(@PathVariable String title){
        TaskTrackerSystem task = null;
        for(TaskTrackerSystem t : taskTrackerSystem){
            if(t.getTitle().equals(title)){
                task=t;
                break;
            }
        }
        return  task;
    }

}
