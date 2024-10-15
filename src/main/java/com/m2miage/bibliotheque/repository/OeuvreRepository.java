package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OeuvreRepository extends JpaRepository<Oeuvre, Long> {
    boolean existsByTitreAndAuteur(String titre, String auteur);
}

