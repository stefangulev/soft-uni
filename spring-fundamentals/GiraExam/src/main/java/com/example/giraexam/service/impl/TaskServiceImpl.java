package com.example.giraexam.service.impl;

import com.example.giraexam.model.entities.Task;
import com.example.giraexam.model.enums.ProgressEnum;
import com.example.giraexam.model.service.AddTaskServiceModel;
import com.example.giraexam.model.views.TaskView;
import com.example.giraexam.repository.TaskRepository;
import com.example.giraexam.service.ClassificationService;
import com.example.giraexam.service.TaskService;
import com.example.giraexam.service.UserService;
import com.example.giraexam.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final ClassificationService classificationService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, ClassificationService classificationService, UserService userService, CurrentUser currentUser) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void addTask(AddTaskServiceModel serviceModel) {
        Task task = modelMapper.map(serviceModel, Task.class);
        task.setClassification(classificationService.findClassificationByName(serviceModel.getClassification())).setProgress(
                ProgressEnum.OPEN).setUser(userService.findUserById(currentUser.getId()));
        taskRepository.save(task);
    }

    @Override
    public List<TaskView> findAllTasks() {
        return taskRepository.findAll().stream().map(t -> modelMapper.map(t, TaskView.class)).collect(Collectors.toList());
    }

    @Override
    public void progressTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);

        switch (task.getProgress()) {
            case OPEN:
                taskRepository.save(task.setProgress(ProgressEnum.IN_PROGRESS));
                break;
            case IN_PROGRESS:
                taskRepository.save(task.setProgress(ProgressEnum.COMPLETED));
                break;
            case COMPLETED: taskRepository.deleteById(id);
            break;
        }


    }
}
