package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.binding.AddNewsBindingModel;
import com.example.manchesterunitedfan.model.binding.EditArticleBindingModel;
import com.example.manchesterunitedfan.model.service.AddNewsServiceModel;
import com.example.manchesterunitedfan.model.service.EditArticleServiceModel;
import com.example.manchesterunitedfan.service.NewsArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final ModelMapper modelMapper;
    private final NewsArticleService newsArticleService;

    public NewsController(ModelMapper modelMapper, NewsArticleService newsArticleService) {
        this.modelMapper = modelMapper;
        this.newsArticleService = newsArticleService;
    }

    @GetMapping
    public String getNews(Model model) {
        model.addAttribute("newsArticles", newsArticleService.getArticles());
        return "news";
    }

    @GetMapping("/add")
    public String getAddNews() {
        return "add-news-article";
    }
    @PostMapping("/add")
    public String postAddNews(@Valid AddNewsBindingModel addNewsBindingModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addNewsBindingModel", addNewsBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewsBindingModel", bindingResult);
            return "redirect:add";
        }
        newsArticleService.addArticle(modelMapper.map(addNewsBindingModel, AddNewsServiceModel.class));

        return "redirect:";
    }

    @ModelAttribute
    public AddNewsBindingModel getAddNewsBindingModel() {
        return new AddNewsBindingModel();
    }
    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        model.addAttribute("article",newsArticleService.findArticleById(id));
        return "news-article-details";

    }


    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable Long id, Model model) {

        model.addAttribute("article", modelMapper.map(newsArticleService.findArticleById(id), EditArticleBindingModel.class));
        return "edit-news-article";
    }

    @GetMapping("/{id}/edit/errors")
    public String getUpdateOfferErrors(@PathVariable Long id) {
        return "edit-news-article";
    }


    @PatchMapping("/edit/{id}")
    public String patchEdit(@PathVariable Long id, @Valid EditArticleBindingModel editArticleBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("article", editArticleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.article", bindingResult);
            return "redirect:/news/" + id + "/edit/errors";
        }

        newsArticleService.updateNewsArticle(modelMapper.map(editArticleBindingModel, EditArticleServiceModel.class), id);

        return "redirect:/news";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        newsArticleService.deleteArticle(id);
        return "redirect:/news";
    }



}
