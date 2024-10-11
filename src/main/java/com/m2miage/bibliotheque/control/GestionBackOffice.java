package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.Usager;
import com.m2miage.bibliotheque.repository.UsagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionBackOffice {
    @Autowired
    private UsagerRepository usagerRepository;

    public String creerUsager(String nom, String prenom) {
        if (usagerRepository.existsByNomAndPrenom(nom, prenom)) {
            return "L'usager existe déjà.";
        } else {
            Usager newUsager = new Usager(nom, prenom);
            usagerRepository.save(newUsager);
            return "Usager créé avec succès.";
        }
    }
}
