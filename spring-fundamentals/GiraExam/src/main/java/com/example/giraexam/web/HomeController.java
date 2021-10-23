package com.example.giraexam.web;

import com.example.giraexam.service.TaskService;
import com.example.giraexam.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        if(!currentUser.isLoggedIn()) {
            return "index";
        }
        model.addAttribute("tasks", taskService.findAllTasks());
        return "home";
    }
}
