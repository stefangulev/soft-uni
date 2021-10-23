package com.example.mymusic.repositories;

import com.example.mymusic.model.entities.Artist;
import com.example.mymusic.model.entities.ArtistNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findArtistByName(ArtistNameEnum artistNameEnum);
}
