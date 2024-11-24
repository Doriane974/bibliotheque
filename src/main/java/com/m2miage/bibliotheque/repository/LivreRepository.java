package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Livre;
import com.m2miage.bibliotheque.entity.Usager;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findAll(Sort sort);
}

