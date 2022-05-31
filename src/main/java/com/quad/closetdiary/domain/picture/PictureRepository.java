package com.quad.closetdiary.domain.picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("SELECT p FROM Picture p ORDER BY p.id")
    List<Picture> findAll();
}
