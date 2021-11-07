package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.services.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public String getStats(Model model) {
        model.addAttribute("stats",statsService.getStats());
        return "stats";
    }
}
