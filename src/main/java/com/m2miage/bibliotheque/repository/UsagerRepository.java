package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsagerRepository extends JpaRepository<Usager, Long> {
    boolean existsByNomAndPrenom(String nom, String prenom);
}
