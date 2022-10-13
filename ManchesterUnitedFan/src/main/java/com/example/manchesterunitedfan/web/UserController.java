package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.ChangePasswordBindingModel;
import com.example.manchesterunitedfan.model.binding.DepositFundsBindingModel;
import com.example.manchesterunitedfan.model.binding.UpdateProfilePictureBindingModel;
import com.example.manchesterunitedfan.model.binding.UserRegisterBindingModel;
import com.example.manchesterunitedfan.model.service.ChangePasswordServiceModel;
import com.example.manchesterunitedfan.model.service.DepositFundsServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProfileServiceModel;
import com.example.manchesterunitedfan.model.service.UserRegisterServiceModel;
import com.example.manchesterunitedfan.service.StadiumVisitService;
import com.example.manchesterunitedfan.service.UserService;
import com.example.manchesterunitedfan.service.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
                userRegisterBindingModel.getEmail()).size() > 0) {
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
        if(!model.containsAttribute("passwordChanged")) {
            model.addAttribute("passwordChanged", false);
        }
        if(!model.containsAttribute("successfulDeposit")) {
            model.addAttribute("successfulDeposit", false);
        }
        if(!model.containsAttribute("pictureUpdated")) {
            model.addAttribute("pictureUpdated", false);
        }
        return "profile";
    }
    @PatchMapping("/profile/change-password")
    public String changePassword(@Valid ChangePasswordBindingModel changePasswordBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 Principal principal) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changePasswordBindingModel", changePasswordBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordBindingModel", bindingResult);
            return "redirect:/users/profile";
        }
        if(!changePasswordBindingModel.getPassword().equals(changePasswordBindingModel.getConfrimPassword())) {
            redirectAttributes.addFlashAttribute("changePasswordBindingModel", changePasswordBindingModel);
            redirectAttributes.addFlashAttribute("differentPasswords", true);
            return "redirect:/users/profile";
        }

        userService.changePassword(modelMapper.map(changePasswordBindingModel,
                ChangePasswordServiceModel.class), principal.getName());
        redirectAttributes.addFlashAttribute("passwordChanged", true);
        return "redirect:/users/profile";
    }
    @ModelAttribute
    public ChangePasswordBindingModel getUpdateProfileBindingModel() {
        return new ChangePasswordBindingModel();
    }

    @PatchMapping("/profile/deposit")
    public String depositFunds(@Valid DepositFundsBindingModel depositFundsBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if(bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("depositFundsBindingModel", depositFundsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.depositFundsBindingModel", bindingResult);
            return "redirect:/users/profile";
        }
        userService.depositFunds(modelMapper.map(depositFundsBindingModel, DepositFundsServiceModel.class), principal.getName());
        redirectAttributes.addFlashAttribute("successfulDeposit", true);
        return "redirect:/users/profile";
    }
    @ModelAttribute
    public DepositFundsBindingModel getDepositFundsBindingModel() {
        return new DepositFundsBindingModel();
    }

    @PatchMapping("/profile/update-picture")
    public String updatePicture(@Valid UpdateProfilePictureBindingModel updateProfilePictureBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Principal principal) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateProfilePictureBindingModel", updateProfilePictureBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateProfilePictureBindingModel", bindingResult);
            return "redirect:/users/profile";
        }
        userService.updateProfilePicture(modelMapper.map(updateProfilePictureBindingModel, UpdateProfileServiceModel.class),
                principal.getName());
        redirectAttributes.addFlashAttribute("pictureUpdated", true);
        return "redirect:/users/profile";
    }

    @ModelAttribute
    public UpdateProfilePictureBindingModel getUpdateProfilePictureBidingModel() {
        return new UpdateProfilePictureBindingModel();
    }
    @ExceptionHandler({UserNotFoundException.class})
    public ModelAndView handleUserNotFoundException(UserNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("user-not-found");
        modelAndView.addObject("exMessage", ex.getMessage());
        return modelAndView;
    }

}
