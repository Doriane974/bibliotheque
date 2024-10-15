package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.Oeuvre;
import com.m2miage.bibliotheque.entity.Usager;
import com.m2miage.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionBackOffice {
    @Autowired
    private UsagerRepository usagerRepository;
    @Autowired
    private OeuvreRepository oeuvreRepository;

    public String creerUsager(String nom, String prenom) {
        if (usagerRepository.existsByNomAndPrenom(nom, prenom)) {
            return "L'usager existe déjà.";
        } else {
            Usager newUsager = new Usager(nom, prenom);
            usagerRepository.save(newUsager);
            return "Usager créé avec succès.";
        }
    }

    public String creerOeuvre(String titre, String auteur){
        if(oeuvreRepository.existsByTitreAndAuteur(titre, auteur)){
            return "L'oeuvre est deja enregistrée.";
        }else{
            Oeuvre newOeuvre = new Oeuvre(titre, auteur);
            oeuvreRepository.save(newOeuvre);
            return "Oeuvre créée avec succès.";
        }
    }
}
