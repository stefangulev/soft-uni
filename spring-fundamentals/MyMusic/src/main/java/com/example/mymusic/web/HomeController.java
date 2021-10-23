package com.example.mymusic.web;

import com.example.mymusic.model.view.AlbumViewModel;
import com.example.mymusic.service.AlbumService;
import com.example.mymusic.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        if(!currentUser.isLoggedIn()) {
            return "index";
        }
        model.addAttribute("albums", albumService.getAllAlbumsByCopiesInDescOrder());
        model.addAttribute("totalCopies", albumService.getAllAlbumsByCopiesInDescOrder().stream()
        .map(AlbumViewModel::getCopies).reduce(Integer::sum).orElse(0));
        return "home";
    }
}
