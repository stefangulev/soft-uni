package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.NewCommentBindingModel;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.validation.ApiError;
import com.example.pathfinder.model.view.CommentView;
import com.example.pathfinder.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentsRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentsRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentView>> getComments(@PathVariable Long routeId) {
        return ResponseEntity.ok(commentService.getComments(routeId));
    }

    @PostMapping("/api/{routeId}/comments")
    public ResponseEntity<CommentView>
    postComment(@AuthenticationPrincipal Principal principal,
                @PathVariable Long routeId,
                @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
               ) {
        CommentServiceModel serviceModel = modelMapper.map(newCommentBindingModel, CommentServiceModel.class);
        serviceModel.setRouteId(routeId);
        CommentView comment = commentService.createComment(serviceModel);
        URI location = URI.create(String.format("/api/%s/comments/%s", routeId, comment.getCommentId()));
        return ResponseEntity.created(location).body(comment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        ex.getFieldErrors().forEach(fe -> apiError.addFiledWithErrors(fe.getField()));
        return ResponseEntity.badRequest().body(apiError);
    }
}
