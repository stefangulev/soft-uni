package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.AddProductBindingModel;
import com.example.manchesterunitedfan.model.binding.UpdateProductBindingModel;
import com.example.manchesterunitedfan.model.entities.ProductEntity;
import com.example.manchesterunitedfan.model.entities.UserEntity;
import com.example.manchesterunitedfan.model.service.AddProductServiceModel;
import com.example.manchesterunitedfan.model.service.UpdateProductServiceModel;
import com.example.manchesterunitedfan.service.ProductService;
import com.example.manchesterunitedfan.service.UserService;
import com.example.manchesterunitedfan.service.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public StoreController(ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String getStore(Model model, Principal principal) {
        if(userService.isAdmin(principal.getName())) {
            model.addAttribute("products", productService.findAllProducts());
            return "store-page";
        }
        model.addAttribute("products", productService.findAllActiveProducts());
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
    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @GetMapping("/add-product")
    public String getAddProduct(Principal principal) {
        return "add-product";
    }
    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @PostMapping("/add-product")
    public String postAddProduct(@Valid AddProductBindingModel addProductBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductBindingModel", addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel", bindingResult);
            return "redirect:add-product";
        }

        productService.createProduct(modelMapper.map(addProductBindingModel, AddProductServiceModel.class));
        return "redirect:";
    }
    @ModelAttribute
    public AddProductBindingModel getAddProductBindingModel() {
        return new AddProductBindingModel();
    }

    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @GetMapping("/edit-product/{id}")
    public String getEditProduct(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("product", productService.getProductViewById(id));
        return "edit-product";
    }
    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @PatchMapping("/edit-product/{id}")
    public String patchEditProduct(@PathVariable Long id, @Valid UpdateProductBindingModel updateProductBindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        if(bindingResult.hasErrors()) {
            updateProductBindingModel.setId(id);
            redirectAttributes.addFlashAttribute("product", updateProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return "redirect:" + id + "/errors";
        }

        productService.updateProduct(modelMapper.map(updateProductBindingModel, UpdateProductServiceModel.class));

        return "redirect:/store/details/" + id ;
    }
    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @GetMapping("/edit-product/{id}/errors")
    public String getErrors(@PathVariable Long id, Model model, Principal principal) {
        if(!model.containsAttribute("product")) {
            UpdateProductBindingModel updateProductBidningModel = getUpdateProductBidningModel();
            updateProductBidningModel.setId(id);
            model.addAttribute("product", updateProductBidningModel);
        }
        return "edit-product";
    }
    @ModelAttribute
    public UpdateProductBindingModel getUpdateProductBidningModel() {
        return new UpdateProductBindingModel();
    }

    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @DeleteMapping("/disable/{id}")
    public String disableProduct(@PathVariable Long id, Principal principal) {
        productService.disableProduct(id);
        return "redirect:/store";
    }
    @PreAuthorize("@userServiceImpl.isAdmin(#principal.name)")
    @PatchMapping("/enable/{id}")
    public String enableProduct(@PathVariable Long id, Principal principal) {
        productService.enableProduct(id);
        return "redirect:/store";
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ModelAndView handleProductException(ProductNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("exMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
