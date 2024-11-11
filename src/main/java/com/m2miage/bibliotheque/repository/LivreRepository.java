package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    //boolean existsByRevueAndNumero(String revue, String numero);
}

