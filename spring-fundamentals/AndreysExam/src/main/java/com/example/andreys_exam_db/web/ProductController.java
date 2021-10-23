package com.example.andreys_exam_db.web;

import com.example.andreys_exam_db.model.binding.AddProductBingingModel;
import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;
import com.example.andreys_exam_db.model.service.AddProductServiceModel;
import com.example.andreys_exam_db.services.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAdd(Model model){
        if(!model.containsAttribute("categories")) {
            model.addAttribute("categories", CategoryEnum.values());
        }
        if(!model.containsAttribute("sexes")) {
            model.addAttribute("sexes", SexEnum.values());
        }

        return "add-product";
    }
    @PostMapping("/add")
    public String postAdd(@Valid AddProductBingingModel addProductBingingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductModel", addProductBingingModel);
            redirectAttributes.addFlashAttribute("categories", CategoryEnum.values());
            redirectAttributes.addFlashAttribute("sexes", SexEnum.values());
            return "redirect:add";
        }

        productService.addProduct(modelMapper.map(addProductBingingModel, AddProductServiceModel.class));
        return "redirect:/";
    }

    @ModelAttribute("addProductModel")
    public AddProductBingingModel getAddProductBingingModel(){
        return new AddProductBingingModel();
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable UUID id, Model model) {
        model.addAttribute("product", productService.findProductDetails(id));
        return "details-product";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
