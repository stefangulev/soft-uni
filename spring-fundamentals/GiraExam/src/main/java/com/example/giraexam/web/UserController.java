package com.example.giraexam.web;

import com.example.giraexam.model.binding.UserLoginBindingModel;
import com.example.giraexam.model.binding.UserRegisterBindingModel;
import com.example.giraexam.model.service.UserLoginServiceModel;
import com.example.giraexam.model.service.UserRegisterServiceModel;
import com.example.giraexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if(!model.containsAttribute("userAlreadyExists")) {
            model.addAttribute("userAlreadyExists", false);
        }
        if(!model.containsAttribute("passwordsNotMatching")) {
            model.addAttribute("passwordsNotMatching", false);
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
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("passwordsNotMatching", true);
            return "redirect:register";
        }
        UserRegisterServiceModel serviceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
        if (userService.userWithUsernameOrEmailExists(serviceModel)) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            return "redirect:register";
        }

        userService.register(serviceModel);

        return "redirect:login";
    }
    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel getRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        if(!model.containsAttribute("invalidCombination")) {
            model.addAttribute("invalidCombination", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        UserLoginServiceModel serviceModel = modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);
        if(!userService.userWithEmailOrPasswordExists(serviceModel)) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("invalidCombination", true);
            return "redirect:login";
        }

        userService.login(serviceModel);
        return "redirect:/";
    }
    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel getUserLoginBindingModel() {
        return  new UserLoginBindingModel();
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
