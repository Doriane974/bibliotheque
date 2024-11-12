package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Exemplaire;
import com.m2miage.bibliotheque.entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsagerRepository extends JpaRepository<Usager, Long> {
    boolean existsByNomAndPrenom(String nom, String prenom);
}
