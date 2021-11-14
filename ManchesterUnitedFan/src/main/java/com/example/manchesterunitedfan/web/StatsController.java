package com.example.manchesterunitedfan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/stats")
public class StatsController {
    @GetMapping
    public String getStats() {
        return "stats";
    }
}
