package com.example.andreys_exam_db.web;

import com.example.andreys_exam_db.model.binding.UserLoginBindingModel;
import com.example.andreys_exam_db.model.binding.UserRegisterBindingModel;
import com.example.andreys_exam_db.model.service.UserLoginServiceModel;
import com.example.andreys_exam_db.model.service.UserRegisterServiceModel;
import com.example.andreys_exam_db.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
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
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword()) ||
                userService.userExistsByUsernameAndEmail(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class)) ) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
            return "redirect:register";
        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:login";
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel getRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !userService.userExistsByUsernameAndPassword(
                modelMapper.map(userLoginBindingModel, UserRegisterServiceModel.class)
        )) {
            redirectAttributes.addFlashAttribute("userModel", userLoginBindingModel);
            return "redirect:login";
        }
        userService.login(modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class));
        return "redirect:/";
    }

    @ModelAttribute("userModel")
    public UserLoginBindingModel getUserLoginModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/logout")
    public String getLogin(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
