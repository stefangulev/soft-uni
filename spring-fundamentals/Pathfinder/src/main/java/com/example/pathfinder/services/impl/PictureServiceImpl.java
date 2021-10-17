package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.view.PictureViewModel;
import com.example.pathfinder.repositories.PictureRepository;
import com.example.pathfinder.services.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Set<PictureViewModel> getAll() {
        return pictureRepository.findAll().stream().map(p -> modelMapper.map(p, PictureViewModel.class)).collect(Collectors.toSet());
    }
}
