package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping("/users/login")
    public String getLoginPage(Model model) {
        if(!model.containsAttribute("invalid_credentials")) {
            model.addAttribute("invalid_credentials", false);
        }
        if(!model.containsAttribute("username")) {
            model.addAttribute("username", "");
        }

        return "auth-login";
    }
    @PostMapping("/users/login-error")
    public String postLoginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                 String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("invalid_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

}
