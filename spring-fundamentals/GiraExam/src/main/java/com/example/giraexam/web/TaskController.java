package com.example.giraexam.web;

import com.example.giraexam.model.binding.AddTaskBindingModel;
import com.example.giraexam.model.service.AddTaskServiceModel;
import com.example.giraexam.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
        public String getAdd() {
        return "add-task";
    }

    @PostMapping("/add")
    public String postAdd(@Valid AddTaskBindingModel addTaskBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTaskBindingModel", addTaskBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTaskBindingModel", bindingResult);
            return "redirect:add";
        }

        taskService.addTask(modelMapper.map(addTaskBindingModel, AddTaskServiceModel.class));

        return "redirect:/";
    }
    @ModelAttribute("addTaskBindingModel")
    public AddTaskBindingModel getAddTaskBindingModel() {
        return new AddTaskBindingModel();
    }

    @PatchMapping("/update/{id}")
    public String patchTask(@PathVariable Long id) {
        taskService.progressTask(id);
        return "redirect:/";
    }
}
