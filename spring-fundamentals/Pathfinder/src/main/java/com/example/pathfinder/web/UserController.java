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

@Controller
@RequestMapping("/users")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @ModelAttribute("userModel")
    public UserLoginBindingModel loginBindModel() {
        return new UserLoginBindingModel();
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String submitLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingRequest.userLoginBindingModel");
            return "redirect:/users/login";
        }
        UserLoginServiceModel serviceModel = modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);
        if (!userService.loginPasswordCombinationValid(serviceModel)) {
            redirectAttributes.addFlashAttribute("userModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("invalidCredentials", true);
            return "redirect:/users/login";
        }
        userService.login(serviceModel);
        return "redirect:/";
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel registerBindModel() {
        return new UserRegisterBindingModel();
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String submitRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingRequest.userRegisterBindingModel");
            return "redirect:/users/register";
        }
        UserRegisterServiceModel serviceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
        if (userService.userExists(serviceModel)) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
//            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
//            redirectAttributes.addFlashAttribute("differentPasswords", true);
            return "redirect:/users/register";
        }

        userService.register(serviceModel);
        return "redirect:/users/login";

    }
    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }
    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        ProfileViewModel profileViewModel = userService.showProfile(id);
        model.addAttribute("viewModel", profileViewModel);
        return "profile";
    }
}
