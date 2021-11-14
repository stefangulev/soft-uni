package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.view.ProductDetailsView;
import com.example.manchesterunitedfan.service.ProductService;
import com.example.manchesterunitedfan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final ProductService productService;
    private final UserService userService;

    public StoreController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String getStore(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "store-page";
    }
    @GetMapping("/details/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        if(!model.containsAttribute("insufficientFunds")) {
            model.addAttribute("insufficientFunds", false);
        }
        ProductEntity productEntityById = productService.getProductEntityById(id);
        if(productEntityById.getQuantity() <= 0) {
            model.addAttribute("outOfStock", true);
        } else {
            model.addAttribute("outOfStock", false);
        }
        model.addAttribute("product", productEntityById);
        return "product-details";
    }
    @PostMapping("/buy/{id}")
    public String buyItem(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        UserEntity buyer = userService.findUserEntityByUsername(principal.getName());
        ProductEntity product = productService.getProductEntityById(id);
        if(buyer.getAccountBalance().subtract(product.getPrice()).doubleValue() < 0) {
            redirectAttributes.addFlashAttribute("insufficientFunds", true);
            return "redirect:/store/details/" + id;
        }
        if(product.getQuantity() <= 0) {
            return "redirect:/store/details/" + id;
        }
        userService.buyProduct(buyer, product);
        return "redirect:/users/profile";

    }
}
