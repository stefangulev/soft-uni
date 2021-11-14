package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.model.entities.StadiumVisitEntity;
import com.example.manchesterunitedfan.model.service.AddStadiumVisitServiceModel;
import com.example.manchesterunitedfan.model.view.StadiumVisitView;
import com.example.manchesterunitedfan.repository.StadiumVisitRepository;
import com.example.manchesterunitedfan.service.StadiumVisitService;
import com.example.manchesterunitedfan.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public void addStadiumVisit(AddStadiumVisitServiceModel serviceModel, String username) {
        StadiumVisitEntity visit = modelMapper.map(serviceModel, StadiumVisitEntity.class);
        visit.setUser(userService.findUserEntityByUsername(username));
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

    @Override
    public List<StadiumVisitView> findVisitsByUsername(String username) {
        return stadiumVisitRepository.findAllByUser_UsernameOrderByDate(username)
                .stream().map(s -> modelMapper.map(s, StadiumVisitView.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteVisit(Long id) {
        stadiumVisitRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String username, Long id) {
        Optional<StadiumVisitEntity> byId = stadiumVisitRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        return byId.get().getUser().getUsername().equals(username);
    }
}
