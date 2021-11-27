package com.example.pathfinder.services;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.view.RouteCardView;
import com.example.pathfinder.model.view.RouteDetailsView;

import java.util.List;

public interface RouteService {
    List<RouteCardView> getAllRoutesView();
    RouteDetailsView getRouteViewById(Long id);
    Route getRouteEntityById(Long id);
}
