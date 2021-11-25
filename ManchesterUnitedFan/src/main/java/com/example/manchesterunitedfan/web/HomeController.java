package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.service.NewsArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    private final NewsArticleService newsArticleService;

    public HomeController(NewsArticleService newsArticleService) {
        this.newsArticleService = newsArticleService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("latestArticle",newsArticleService.findLatestArticle());
        return "index";
    }
}
