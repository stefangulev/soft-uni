package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.AddPlayerBindingModel;
import com.example.manchesterunitedfan.model.binding.EditPlayerBindingModel;
import com.example.manchesterunitedfan.model.service.AddPlayerServiceModel;
import com.example.manchesterunitedfan.model.service.EditPlayerServiceModel;
import com.example.manchesterunitedfan.service.PlayerService;
import com.example.manchesterunitedfan.service.exceptions.PlayerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/team")
public class TeamController {
    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    public TeamController(PlayerService playerService, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/squad")
    public String getSquad(Model model) {
        model.addAttribute("players", playerService.findSquad());
        return "squad";
    }
    @GetMapping("/squad/details/{id}")
    public String getPlayerDetails(@PathVariable Long id, Model model) {
        model.addAttribute("player", playerService.findPlayerById(id));
        return "player-details";
    }
    @GetMapping("/squad/add")
    public String getAddPlayer(Model model){
        if(!model.containsAttribute("squadNumberTaken")) {
            model.addAttribute("squadNumberTaken", false);
        }
        return "add-player";
    }

    @PostMapping("/squad/add")
    public String postAddPlayer(@Valid AddPlayerBindingModel addPlayerBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPlayerBindingModel", addPlayerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPlayerBindingModel", bindingResult);
            return "redirect:add";
        }
        if(playerService.squadNumberTaken(addPlayerBindingModel.getSquadNumber())) {
            redirectAttributes.addFlashAttribute("addPlayerBindingModel", addPlayerBindingModel);
            redirectAttributes.addFlashAttribute("squadNumberTaken", true);
            return "redirect:add";
        }
        playerService.addPlayer(modelMapper.map(addPlayerBindingModel, AddPlayerServiceModel.class));
            return "redirect:";
    }
    @ModelAttribute
    public AddPlayerBindingModel getAddPlayerBindingModel() {
        return new AddPlayerBindingModel();
    }
    @ModelAttribute
    public EditPlayerBindingModel getEditPlayerBindingModel() {
        return new EditPlayerBindingModel();
    }

    @GetMapping("/squad/edit/{id}")
    public String getEditPlayer(@PathVariable Long id, Model model) {
        model.addAttribute("player", playerService.findPlayerById(id));
        if(!model.containsAttribute("squadNumberTaken")) {
            model.addAttribute("squadNumberTaken", false);
        }
        return "edit-player";
    }
    @GetMapping("/squad/edit/{id}/errors")
    public String getEditPlayerErrors(@PathVariable Long id, Model model) {
        if(!model.containsAttribute("squadNumberTaken")) {
            model.addAttribute("squadNumberTaken", false);
        }
        return "edit-player";
    }

    @PostMapping("/squad/edit/{id}")
    public String getEditPlayer(@PathVariable Long id, @Valid EditPlayerBindingModel editPlayerBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        editPlayerBindingModel.setId(id);

            if(bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("player", editPlayerBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.player", bindingResult);
                return "redirect:/team/squad/edit/" + id + "/errors";
            }
            if(playerService.squadNumberTaken(editPlayerBindingModel.getSquadNumber(), id)) {
                redirectAttributes.addFlashAttribute("player", editPlayerBindingModel);
                redirectAttributes.addFlashAttribute("squadNumberTaken", true);
                return "redirect:/team/squad/edit/" + id + "/errors";
            }

        playerService.editPlayer(modelMapper.map(editPlayerBindingModel, EditPlayerServiceModel.class).setId(id));
        return "redirect:/team/squad/details/" + id;
    }


    @DeleteMapping("/squad/delete/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "redirect:/team/squad";
    }

    @GetMapping("/standings")
    public String getStandings() {

        return "standings";
    }
    @GetMapping("/matches")
    public String getMatches(){
        return "matches";
    }

    @ExceptionHandler({PlayerNotFoundException.class})
    public ModelAndView handlePlayerException(PlayerNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("player-not-found");
        modelAndView.addObject("exMessage", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
