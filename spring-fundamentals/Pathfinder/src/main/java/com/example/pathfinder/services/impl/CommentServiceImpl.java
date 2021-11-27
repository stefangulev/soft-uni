package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.view.CommentView;
import com.example.pathfinder.model.view.RouteDetailsView;
import com.example.pathfinder.repositories.CommentRepository;
import com.example.pathfinder.services.CommentService;
import com.example.pathfinder.services.RouteService;
import com.example.pathfinder.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final RouteService routeService;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, RouteService routeService, UserService userService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

        this.routeService = routeService;
        this.userService = userService;
    }

    @Override
    public List<CommentView> getComments(Long routeId) {
        return commentRepository.findCommentByRoute_Id(routeId).stream()
                .map(this::mapAsComment).collect(Collectors.toList());
    }

    @Override
    public CommentView createComment(CommentServiceModel commentServiceModel) {
        Route route = routeService.getRouteEntityById(commentServiceModel.getRouteId());
        User user = userService.findByUsername(commentServiceModel.getCreator());

        Comment comment = new Comment()
                .setTextContent(commentServiceModel
                        .getMessage())
                .setAuthor(user)
                .setRoute(route).setCreated(LocalDateTime.now()).setApproved(false);

        Comment savedComment = commentRepository.save(comment);


        return mapAsComment(savedComment);
    }

    private CommentView mapAsComment(Comment commentEntity) {
        CommentView commentViewModel = new CommentView();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getFullName());


        return commentViewModel;
    }
}
