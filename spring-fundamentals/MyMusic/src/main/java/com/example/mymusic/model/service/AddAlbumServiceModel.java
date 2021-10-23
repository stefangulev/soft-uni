package com.example.mymusic.model.service;

import com.example.mymusic.model.entities.AlbumGenreEnum;
import com.example.mymusic.model.entities.ArtistNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddAlbumServiceModel {
    private String name;
    private String imageUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private String producer;
    private AlbumGenreEnum genre;
    private ArtistNameEnum artist;

    public String getName() {
        return name;
    }

    public AddAlbumServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddAlbumServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAlbumServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AddAlbumServiceModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddAlbumServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public AddAlbumServiceModel setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AddAlbumServiceModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AddAlbumServiceModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AddAlbumServiceModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }
}
