package com.example.manchesterunitedfan.service;

import com.example.manchesterunitedfan.model.service.AddStadiumVisitServiceModel;
import com.example.manchesterunitedfan.model.view.StadiumVisitView;

import java.util.List;

public interface StadiumVisitService {
    void addStadiumVisit(AddStadiumVisitServiceModel serviceModel, String username);
    List<StadiumVisitView> findAllStadiumVisits();
    List<StadiumVisitView> findVisitsByUsername(String username);
    void deleteVisit(Long id);
    boolean isOwner(String username, Long id);
}
