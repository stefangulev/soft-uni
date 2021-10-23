package com.example.mymusic.service.impl;

import com.example.mymusic.model.entities.Album;
import com.example.mymusic.model.service.AddAlbumServiceModel;
import com.example.mymusic.model.view.AlbumViewModel;
import com.example.mymusic.repositories.AlbumRepository;
import com.example.mymusic.service.AlbumService;
import com.example.mymusic.service.ArtistService;
import com.example.mymusic.service.UserService;
import com.example.mymusic.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository, CurrentUser currentUser, UserService userService, ModelMapper modelMapper, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
    }

    @Override
    public void addAlbum(AddAlbumServiceModel addAlbumServiceModel) {
        Album album = modelMapper.map(addAlbumServiceModel, Album.class)
                .setAddedFrom(userService.findUserById(currentUser.getId()))
                .setArtist(artistService.findArtistByName(addAlbumServiceModel.getArtist()));
        albumRepository.save(album);
    }

    @Override
    public List<AlbumViewModel> getAllAlbumsByCopiesInDescOrder() {
        return albumRepository.findAllByOrderByCopiesDesc().stream().map(a -> modelMapper.map(a, AlbumViewModel.class
        )).collect(Collectors.toList());
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
