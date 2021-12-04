package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.ChangeRoleBindingModel;
import com.example.manchesterunitedfan.model.service.ChangeRoleServiceModel;
import com.example.manchesterunitedfan.service.RoleService;
import com.example.manchesterunitedfan.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/stats")
    public String getStats() {
        return "stats";
    }

    @GetMapping("/roles")
    public String getRoles(Model model) {
        if(!model.containsAttribute("invalidOption")){
            model.addAttribute("invalidOption", false);
        }
        if(!model.containsAttribute("changeCompleted")) {
            model.addAttribute("invalidOption", false);
        }
        model.addAttribute("usersWithRoles", userService.getAllUsersWithRoles());
        return "roles";
    }

    @PatchMapping("/roles/{id}")
    public String changeRole(@Valid ChangeRoleBindingModel changeRoleBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,  Principal principal, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("invalidOption", true);
            return "redirect:/admin/roles";
        }

        roleService.updateRole(modelMapper.map(changeRoleBindingModel, ChangeRoleServiceModel.class), id);
        redirectAttributes.addFlashAttribute("changeCompleted", true);
        if(principal.getName().equals(userService.findUserEntityById(id).getUsername())) {
            SecurityContextHolder.clearContext();
            return "redirect:/users/login";
        }
        return "redirect:/admin/roles";
    }

    @ModelAttribute
    public ChangeRoleBindingModel getChangeRoleBindingModel() {
        return new ChangeRoleBindingModel();
    }

}
