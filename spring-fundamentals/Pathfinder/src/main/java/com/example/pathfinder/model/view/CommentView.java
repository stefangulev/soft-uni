package com.example.pathfinder.model.view;

import java.time.LocalDateTime;

public class CommentView {
    private Long commentId;
    private String message;
    private String user;
    private LocalDateTime created;
    private boolean canApprove;
    private boolean canDelete;

    public Long getCommentId() {
        return commentId;
    }

    public CommentView setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentView setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentView setUser(String user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentView setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public CommentView setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
