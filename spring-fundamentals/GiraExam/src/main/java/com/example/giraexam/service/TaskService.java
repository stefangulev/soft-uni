package com.example.giraexam.service;

import com.example.giraexam.model.service.AddTaskServiceModel;
import com.example.giraexam.model.views.TaskView;

import java.util.List;

public interface TaskService {
    void addTask(AddTaskServiceModel serviceModel);
    List<TaskView> findAllTasks();

    void progressTask(Long id);
}
