package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    //boolean existsByRevueAndNumero(String revue, String numero);
}

