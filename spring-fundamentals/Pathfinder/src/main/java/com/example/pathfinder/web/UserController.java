package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.binding.UserRegisterBindingModel;
import com.example.pathfinder.model.service.UserLoginServiceModel;
import com.example.pathfinder.model.service.UserRegisterServiceModel;
import com.example.pathfinder.model.view.ProfileViewModel;
import com.example.pathfinder.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @ModelAttribute("userRegisterModel")
    public UserRegisterBindingModel registerBindModel() {
        return new UserRegisterBindingModel();
    }
    @GetMapping("/register")
    public String register(Model model) {
        if(!model.containsAttribute("userExists")) {
            model.addAttribute("userExists", false);
        }
        if(!model.containsAttribute("differentPasswords")) {
            model.addAttribute("differentPasswords", false);
        }

        return "register";
    }
    @PostMapping("/register")
    public String submitRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);
            return "redirect:/users/register";
        }
        UserRegisterServiceModel serviceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
        if (userService.userExists(serviceModel)) {
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("differentPasswords", true);
            return "redirect:/users/register";
        }

        userService.register(serviceModel);
        return "redirect:/users/login";

    }

    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {
        ProfileViewModel profileViewModel = userService.showProfile(principal.getName());
        model.addAttribute("viewModel", profileViewModel);
        return "profile";
    }
}
