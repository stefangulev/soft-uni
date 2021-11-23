package com.example.pathfinder.repositories;

import com.example.pathfinder.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByRoute_Id(Long id);
}
