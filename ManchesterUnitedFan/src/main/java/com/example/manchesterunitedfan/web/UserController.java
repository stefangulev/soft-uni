package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.UpdateProfileBindingModel;
import com.example.manchesterunitedfan.model.binding.UserRegisterBindingModel;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.service.StadiumVisitService;
import com.example.manchesterunitedfan.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final StadiumVisitService stadiumVisitService;

    public UserController(UserService userService, ModelMapper modelMapper, StadiumVisitService stadiumVisitService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.stadiumVisitService = stadiumVisitService;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if(!model.containsAttribute("differentPasswords")) {
            model.addAttribute("differentPasswords", false);
        }
        if(!model.containsAttribute("userExists")) {
            model.addAttribute("userExists", false);
        }
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("differentPasswords", true);
            return "redirect:register";
        }
        if (userService.findUserEntityByUsernameOrEmail(userRegisterBindingModel.getUsername(),
                userRegisterBindingModel.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:register";
        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:/";
    }
    @ModelAttribute
    public  UserRegisterBindingModel getUserRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        if(!model.containsAttribute("username")) {
            model.addAttribute("username", "");
        }
        if(!model.containsAttribute("invalid_credentials")) {
            model.addAttribute("invalid_credentials", false);
        }
        return "login";
    }
    @PostMapping("/login-error")
    public String postLoginError(@ModelAttribute (UsernamePasswordAuthenticationFilter.
            SPRING_SECURITY_FORM_USERNAME_KEY) String username, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("username", username);
        redirectAttributes.addFlashAttribute("invalid_credentials", true);
        return "redirect:login";
    }
    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
       model.addAttribute("user", userService.findUserEntityByUsername(principal.getName()));
       model.addAttribute("stadiumVisits", stadiumVisitService.findVisitsByUsername(principal.getName()));
       if(!model.containsAttribute("differentPasswords")) {
           model.addAttribute("differentPasswords", false);
       }
        return "profile";
    }
    @PatchMapping("/profile")
    public String updateProfile(@Valid UpdateProfileBindingModel updateProfileBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Principal principal) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateProfileBindingModel", updateProfileBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateProfileBindingModel", bindingResult);
            return "redirect:profile";
        }
        if(!updateProfileBindingModel.getPassword().equals(updateProfileBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("updateProfileBindingModel", updateProfileBindingModel);
            redirectAttributes.addFlashAttribute("differentPasswords", true);
            return "redirect:profile";
        }

        userService.updateProfile(modelMapper.map(updateProfileBindingModel,
                UpdateProfileServiceModel.class), principal.getName());

        return "redirect:profile";
    }
    @ModelAttribute
    public UpdateProfileBindingModel getUpdateProfileBindingModel() {
        return new UpdateProfileBindingModel();
    }
}
