package com.example.mymusic.web;

import com.example.mymusic.model.binding.UserLoginBindingModel;
import com.example.mymusic.model.binding.UserRegisterBindingModel;
import com.example.mymusic.model.service.UserLoginServiceModel;
import com.example.mymusic.model.service.UserRegisterServiceModel;
import com.example.mymusic.service.UserService;
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
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword()) || userService.
                userWithEmailOrUsernameExists(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class)) ) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:register";
        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));

        return "redirect:login";
    }
    @ModelAttribute("userModel")
    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        if(!model.containsAttribute("invalidUserPassCombination")) {
            model.addAttribute("invalidUserPassCombination", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        if (!userService.userWithUsernameAndPasswordExists(modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class))) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("invalidUserPassCombination", true);
            return "redirect:login";
        }

        userService.login(modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel getLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
