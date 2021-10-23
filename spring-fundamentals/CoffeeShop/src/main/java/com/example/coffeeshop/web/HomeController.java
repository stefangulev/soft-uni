package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.services.OrderService;
import com.example.coffeeshop.services.UserService;
import com.example.coffeeshop.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if(currentUser.getId() != null) {
            List<OrderViewModel> orders = orderService.findAllOrdersInDescOrder();
            model.addAttribute("orders",orders );
            model.addAttribute("timeToPrepareAll",orders
            .stream().map(o -> o.getCategory().getNeededTime()).reduce(Integer::sum).orElse(0));
            model.addAttribute("users", userService.findUsersOrderedByOrderCountDesc());
            return "home";
        }
        return "index";
    }
}
