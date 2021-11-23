package com.example.pathfinder.services;

import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.view.CommentView;

import java.util.List;

public interface CommentService {
    List<CommentView> getComments(Long routeId);
    CommentView createComment(CommentServiceModel commentServiceModel);
}
