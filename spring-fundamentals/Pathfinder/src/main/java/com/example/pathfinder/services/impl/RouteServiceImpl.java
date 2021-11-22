package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.view.RouteCardView;
import com.example.pathfinder.model.view.RouteDetailsView;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.services.RouteService;
import com.example.pathfinder.web.exceptions.RouteNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteCardView> getAllRoutesView() {
        return routeRepository.findAll().stream().map(r -> {
            RouteCardView viewModel = modelMapper.map(r, RouteCardView.class);
            if(r.getPictures().isEmpty()) {
                viewModel.setPictureUrl("/images/pic4.jpg");
            } else {
                viewModel.setPictureUrl(r.getPictures().stream().findFirst().get().getUrl());
            }
            return viewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public RouteDetailsView getRouteById(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RouteNotFound("Route with ID " + id + " not found!"));
        RouteDetailsView detailsView = modelMapper.map(route, RouteDetailsView.class);
        detailsView.setAuthorName(route.getAuthor().getUsername());
        return detailsView;
    }
}
