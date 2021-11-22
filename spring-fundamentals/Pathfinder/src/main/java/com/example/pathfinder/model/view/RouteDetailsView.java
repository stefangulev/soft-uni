package com.example.pathfinder.model.view;

import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.LevelEnum;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.User;

import java.util.Set;

public class RouteDetailsView {
    private Long id;
    private String gpxCoordinates;
    private LevelEnum level;
    private String name;
    private String description;
    private String authorName;
    private String videoUrl;
    private Set<Picture> pictures;

    public Long getId() {
        return id;
    }

    public RouteDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteDetailsView setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteDetailsView setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public RouteDetailsView setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsView setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public RouteDetailsView setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }
}
