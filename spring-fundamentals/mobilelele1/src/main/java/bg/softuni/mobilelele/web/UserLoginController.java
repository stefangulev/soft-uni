package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserLoginController {
    private final UserService userService;

    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserLoginBindingModel model() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/users/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("auth-login");
        return modelAndView;
    }
    @PostMapping("/users/login")
    public String login(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !userService.loginUser(new UserLoginServiceModel()
                .setUsername(userLoginBindingModel.getUsername())
                .setPassword(userLoginBindingModel.getPassword()))) {

            redirectAttributes.addFlashAttribute("userModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/login";
        }
        return "redirect:/";


    }
}
