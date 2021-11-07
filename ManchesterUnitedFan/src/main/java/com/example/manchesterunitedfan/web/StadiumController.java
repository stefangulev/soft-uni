package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.AddStadiumVisitBindingModel;
import com.example.manchesterunitedfan.model.service.AddStadiumVisitServiceModel;
import com.example.manchesterunitedfan.service.StadiumVisitService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/stadium")
public class StadiumController {

    private final StadiumVisitService stadiumVisitService;
    private final ModelMapper modelMapper;

    public StadiumController(StadiumVisitService stadiumVisitService, ModelMapper modelMapper) {
        this.stadiumVisitService = stadiumVisitService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String getStadium(Model model) {
        model.addAttribute("visits", stadiumVisitService.findAllStadiumVisits());
        return "stadium";
    }
    @PostMapping
    public String postStadium(@Valid AddStadiumVisitBindingModel addStadiumVisitBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes,
                              Principal principal) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addStadiumVisitBindingModel", addStadiumVisitBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addStadiumVisitBindingModel", bindingResult);
            return "redirect:stadium";
        }

        stadiumVisitService.addStadiumVisit(modelMapper.map(addStadiumVisitBindingModel, AddStadiumVisitServiceModel.class), principal.getName());
        return "redirect:stadium";
    }

    @ModelAttribute
    public AddStadiumVisitBindingModel getAddStadiumVisitBindingModel() {
        return new AddStadiumVisitBindingModel();
    }
}
