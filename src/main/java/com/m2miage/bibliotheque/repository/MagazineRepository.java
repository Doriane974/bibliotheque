package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Magazine;
import com.m2miage.bibliotheque.entity.Usager;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    //boolean existsByRevueAndNumero(String revue, String numero);
    List<Magazine> findAll(Sort sort);
}

