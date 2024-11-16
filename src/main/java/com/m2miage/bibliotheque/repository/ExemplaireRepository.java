package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    List<Exemplaire> findByOeuvreId(Long oeuvreId);
}


