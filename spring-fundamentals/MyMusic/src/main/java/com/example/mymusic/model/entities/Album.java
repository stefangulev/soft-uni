package com.example.mymusic.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {
    private String name;
    private String imageUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private String producer;
    private AlbumGenreEnum genre;
    private Artist artist;
    private User addedFrom;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }
    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public Album setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Album setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public Integer getCopies() {
        return copies;
    }

    public Album setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public Album setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    @Column(nullable = false)
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public Album setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
    @Column
    public String getProducer() {
        return producer;
    }

    public Album setProducer(String producer) {
        this.producer = producer;
        return this;
    }
    @Enumerated
    @Column(nullable = false)
    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public Album setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Artist getArtist() {
        return artist;
    }

    public Album setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }
    @ManyToOne
    public User getAddedFrom() {
        return addedFrom;
    }

    public Album setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
