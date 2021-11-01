package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.StadiumVisitEntity;
import com.example.manchesterunitedfan.model.service.AddStadiumVisitServiceModel;
import com.example.manchesterunitedfan.model.view.StadiumVisitView;
import com.example.manchesterunitedfan.repository.StadiumVisitRepository;
import com.example.manchesterunitedfan.service.StadiumVisitService;
import com.example.manchesterunitedfan.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StadiumVisitServiceImpl implements StadiumVisitService {
    private final StadiumVisitRepository stadiumVisitRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public StadiumVisitServiceImpl(StadiumVisitRepository stadiumVisitRepository, ModelMapper modelMapper, UserService userService) {
        this.stadiumVisitRepository = stadiumVisitRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addStadiumVisit(AddStadiumVisitServiceModel serviceModel) {
        StadiumVisitEntity visit = modelMapper.map(serviceModel, StadiumVisitEntity.class);
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        visit.setUser(userService.findUserByUserName(username));
        stadiumVisitRepository.save(visit);
    }

    @Override
    public List<StadiumVisitView> findAllStadiumVisits() {
        return stadiumVisitRepository.findAllByOrderByDate().stream().map(v -> {
            StadiumVisitView view = modelMapper.map(v, StadiumVisitView.class);
            view.setUsername(v.getUser().getUsername());
            return view;
                }
        ).collect(Collectors.toList());
    }
}
