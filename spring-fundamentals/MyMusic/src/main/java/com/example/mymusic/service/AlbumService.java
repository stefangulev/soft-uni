package com.example.mymusic.service;

import com.example.mymusic.model.service.AddAlbumServiceModel;
import com.example.mymusic.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    void addAlbum(AddAlbumServiceModel addAlbumServiceModel);
    List<AlbumViewModel> getAllAlbumsByCopiesInDescOrder();

    void deleteAlbum(Long id);

}
