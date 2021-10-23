package com.example.mymusic.service;

import com.example.mymusic.model.entities.Artist;
import com.example.mymusic.model.entities.ArtistNameEnum;

public interface ArtistService {
    void initializeArtists();
    Artist findArtistByName(ArtistNameEnum artistNameEnum);
}
