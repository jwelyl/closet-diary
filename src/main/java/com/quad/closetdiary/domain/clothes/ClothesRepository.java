package com.quad.closetdiary.domain.clothes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    @Query("SELECT c FROM Clothes c ORDER BY c.id")
    List<Clothes> findAll();
}
