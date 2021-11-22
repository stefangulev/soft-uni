package com.example.pathfinder.services;

import com.example.pathfinder.model.view.RouteCardView;
import com.example.pathfinder.model.view.RouteDetailsView;

import java.util.List;

public interface RouteService {
    List<RouteCardView> getAllRoutesView();
    RouteDetailsView getRouteById(Long id);
}
