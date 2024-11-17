package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.*;
import com.m2miage.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionBackOffice {
    @Autowired
    private UsagerRepository usagerRepository;
    @Autowired
    private OeuvreRepository oeuvreRepository;
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    /////////////////////////////////////////
    //         Gestion des Usagers         //
    /////////////////////////////////////////

    public String creerUsager(String nom, String prenom) {
        if (usagerRepository.existsByNomAndPrenom(nom, prenom)) {
            return "L'usager existe déjà.";
        } else {
            Usager newUsager = new Usager(nom, prenom);
            usagerRepository.save(newUsager);
            return "Usager créé avec succès.";
        }
    }
    public Usager obtenirUsager(Long usagerId) {
        return usagerRepository.findById(usagerId)
                .orElseThrow(() -> new RuntimeException("Usager not found"));
    }
    public List<Usager> ObtenirTousUsagers() {
        return usagerRepository.findAll();
    }
    public void modifierUsager(Long usagerId, String nom, String prenom) {
        Usager usager = obtenirUsager(usagerId);
        if (!nom.isBlank()) usager.setNom(nom);
        if (!prenom.isBlank()) usager.setPrenom(prenom);
        usagerRepository.save(usager);
    }
    public void supprimerUsager(Long usagerId) {
        usagerRepository.deleteById(usagerId);
    }

    /////////////////////////////////////////
    //         Gestion des Oeuvres         //
    /////////////////////////////////////////

    public Oeuvre obtenirOeuvre(Long oeuvreId) {
        return oeuvreRepository.findById(oeuvreId)
                .orElseThrow(() -> new RuntimeException("Oeuvre not found"));
    }
    public List<Oeuvre> obtenirToutesOeuvres() {
        return oeuvreRepository.findAll();
    }

    //--------------------------------------//
    //          Gestion des livres          //
    //--------------------------------------//

    public String creerLivre(String titre, String auteur, String edition, String dateParution) {
        Livre livre = new Livre(titre, dateParution, auteur, edition);
        livreRepository.save(livre);
        return "Livre créé avec succès.";
    }
    public List<Livre> ObtenirTousLivres() {
        return livreRepository.findAll();
    }

    //-------------------------------------//
    //        Gestion des Magazines        //
    //-------------------------------------//

    public String creerMagazine(String titre, String revue, int numero, String dateParution) {
        Magazine magazine = new Magazine(titre, revue, numero, dateParution);
        magazineRepository.save(magazine);
        return "Magazine créé avec succès.";
    }
    public List<Magazine> ObtenirTousMagazines() {
        return magazineRepository.findAll();
    }

    //-------------------------------------//
    //       Gestion des Exemplaires       //
    //-------------------------------------//

    public String ajouterExemplaire(Long oeuvreId, String etat, String disponibilite) {
        Oeuvre oeuvre = oeuvreRepository.findById(oeuvreId)
                .orElseThrow(() -> new RuntimeException("Oeuvre not found"));

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setEtat(etat);
        exemplaire.setDisponibilite(disponibilite);
        exemplaire.setOeuvre(oeuvre);

        exemplaireRepository.save(exemplaire);
        return "Exemplaire ajouté avec succès.";
    }
    public List<Exemplaire> obtenirTousExemplaires(Long oeuvreId) {
        return exemplaireRepository.findByOeuvreId(oeuvreId);
    }
    public Exemplaire obtenirExemplaire(Long exemplaireId) {
        return exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new RuntimeException("Exemplaire not found"));
    }
    public void supprimerExemplaire(Long exemplaireId) {
        exemplaireRepository.deleteById(exemplaireId);
    }
    public void modifierExemplaire(Long exemplaireId, String etat, String disponibilite) {
        Exemplaire exemplaire = obtenirExemplaire(exemplaireId);
        exemplaire.setEtat(etat);
        exemplaire.setDisponibilite(disponibilite);
        exemplaireRepository.save(exemplaire);
    }









}
