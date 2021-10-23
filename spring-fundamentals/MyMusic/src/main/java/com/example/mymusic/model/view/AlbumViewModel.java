package com.example.mymusic.model.view;

import com.example.mymusic.model.entities.AlbumGenreEnum;
import com.example.mymusic.model.entities.Artist;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AlbumViewModel {
    private Long id;
    private String name;
    private String imageUrl;
    private AlbumGenreEnum genre;
    private Artist artist;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private Integer copies;

    public Long getId() {
        return id;
    }

    public AlbumViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public AlbumViewModel setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public AlbumViewModel setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }
}
