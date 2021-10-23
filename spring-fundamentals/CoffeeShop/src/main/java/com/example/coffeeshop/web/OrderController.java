package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.OrderAddBindingModel;
import com.example.coffeeshop.model.entities.CategoryNameEnum;
import com.example.coffeeshop.model.service.OrderAddServiceModel;
import com.example.coffeeshop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if(!model.containsAttribute("categories")) {
            model.addAttribute("categories", CategoryNameEnum.values());
        }
        return "order-add";
    }


    @PostMapping("/add")
    public String postAdd(@Valid OrderAddBindingModel orderAddBindingModel, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);
           redirectAttributes.addFlashAttribute("categories", CategoryNameEnum.values());
            return "redirect:add";
        }
        orderService.addOffer(modelMapper.map(orderAddBindingModel, OrderAddServiceModel.class));
        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel getAddBindingModel() {
        return new OrderAddBindingModel();
    }

    @GetMapping("/ready/{id}")
    public String getReady(@PathVariable Long id) {
        orderService.readyOffer(id);
        return "redirect:/";
    }
}
