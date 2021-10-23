package com.example.andreys_exam_db.web;

import com.example.andreys_exam_db.services.ProductService;
import com.example.andreys_exam_db.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String  getIndex(Model model) {
        if(!currentUser.isLoggedIn()) {
            return "index";
        }
        model.addAttribute("products", productService.findAllProducts());

        return "home";

    }
}
