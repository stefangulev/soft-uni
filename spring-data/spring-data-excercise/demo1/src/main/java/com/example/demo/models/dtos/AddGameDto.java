package com.example.demo.models.dtos;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thubnailURL;
    private String description;
    private LocalDate releaseDate;

    public AddGameDto(String title, BigDecimal price, Double size, String trailer, String thubnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thubnailURL = thubnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "[A-Z]\\w{2,99}", message = "Invalid title!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @DecimalMin(value = "0", message = "Invalid price!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Positive
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = "Invalid trailer!")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?).+", message = "Invalid thumbnail URL!")
    public String getThubnailURL() {
        return thubnailURL;
    }

    public void setThubnailURL(String thubnailURL) {
        this.thubnailURL = thubnailURL;
    }

    @Size(min = 20, message = "Invalid description!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
