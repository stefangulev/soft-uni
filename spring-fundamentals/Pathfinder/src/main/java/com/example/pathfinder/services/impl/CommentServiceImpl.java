package com.example.pathfinder.services.impl;

import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.view.CommentView;
import com.example.pathfinder.model.view.RouteDetailsView;
import com.example.pathfinder.repositories.CommentRepository;
import com.example.pathfinder.services.CommentService;
import com.example.pathfinder.services.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<CommentView> getComments(Long routeId) {
       return commentRepository.findCommentByRoute_Id(routeId).stream()
       .map(this::mapAsComment).collect(Collectors.toList());
    }

    @Override
    public CommentView createComment(CommentServiceModel commentServiceModel) {
        throw new UnsupportedOperationException("not implemented yet");
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
