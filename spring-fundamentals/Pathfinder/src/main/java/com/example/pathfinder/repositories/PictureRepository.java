package com.example.pathfinder.repositories;

import com.example.pathfinder.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
