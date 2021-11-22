package com.example.pathfinder.model.view;

public class RouteCardView {
    private Long id;
    private String name;
    private String description;
    private String pictureUrl;

    public Long getId() {
        return id;
    }

    public RouteCardView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteCardView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteCardView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteCardView setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
