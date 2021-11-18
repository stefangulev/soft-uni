package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.service.PlayerService;
import com.example.manchesterunitedfan.service.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/team")
public class TeamController {
    private final PlayerService playerService;

    public TeamController(PlayerService playerService) {
        this.playerService = playerService;
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
