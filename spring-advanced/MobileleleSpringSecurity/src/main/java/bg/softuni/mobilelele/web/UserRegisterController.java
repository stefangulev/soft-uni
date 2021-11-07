package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private static Logger LOGGER = LoggerFactory.getLogger(UserRegisterController.class);
    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/users/register")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.setViewName("auth-register");
        return modelAndView;
    }
    @PostMapping("/users/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Model model) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }
        UserRegisterServiceModel serviceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

//        if (!userService.usernameFree(serviceModel.getUsername())) {
//           redirectAttributes.addFlashAttribute("userModel", userRegisterBindingModel);
//            redirectAttributes.addFlashAttribute("usernameOccupied", true);
//            return "redirect:/users/register";
//        }
        userService.registerUser(serviceModel);
        return "redirect:/";

    }
}
