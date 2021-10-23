package com.example.mymusic.model.binding;

import com.example.mymusic.model.entities.AlbumGenreEnum;
import com.example.mymusic.model.entities.Artist;
import com.example.mymusic.model.entities.ArtistNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AddAlbumBindingModel {
    private String name;
    private String imageUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private String producer;
    private AlbumGenreEnum genre;
    private ArtistNameEnum artist;

    @NotBlank
    @Size(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public AddAlbumBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @NotBlank
    @Size(min = 5)
    public String getImageUrl() {
        return imageUrl;
    }

    public AddAlbumBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    @NotBlank
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public AddAlbumBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
    @NotNull
    @Min(10)
    public Integer getCopies() {
        return copies;
    }

    public AddAlbumBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }
    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public AddAlbumBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public AddAlbumBindingModel setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AddAlbumBindingModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }
    @NotNull
    public AlbumGenreEnum getGenre() {
        return genre;
    }

    public AddAlbumBindingModel setGenre(AlbumGenreEnum genre) {
        this.genre = genre;
        return this;
    }
    @NotNull
    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AddAlbumBindingModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }
}
