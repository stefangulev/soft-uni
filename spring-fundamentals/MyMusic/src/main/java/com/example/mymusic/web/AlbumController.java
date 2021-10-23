package com.example.mymusic.web;

import com.example.mymusic.model.binding.AddAlbumBindingModel;
import com.example.mymusic.model.service.AddAlbumServiceModel;
import com.example.mymusic.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAdd() {
        return "add-album";
    }

    @PostMapping("/add")
    public String postAdd(@Valid AddAlbumBindingModel addAlbumBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAlbumBindingModel", addAlbumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbumBindingModel", bindingResult);
            return "redirect:add";
        }
        albumService.addAlbum(modelMapper.map(addAlbumBindingModel, AddAlbumServiceModel.class));
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return "redirect:/";
    }

    @ModelAttribute("addAlbumBindingModel")
    public AddAlbumBindingModel getAddAlbumBindingModel() {
        return new AddAlbumBindingModel();
    }


}
