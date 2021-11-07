package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
