    package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.*;
import com.m2miage.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


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
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmpruntRepository empruntRepository;


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
        return usagerRepository.findAll(Sort.by(Sort.Direction.ASC, "nom", "prenom"));
    }
    public void modifierUsager(Long usagerId, String nom, String prenom) {
        Usager usager = obtenirUsager(usagerId);
        if (!nom.isBlank()) usager.setNom(nom);
        if (!prenom.isBlank()) usager.setPrenom(prenom);
        usagerRepository.save(usager);
    }
    public String supprimerUsager(Long usagerId) {
        Usager usager = obtenirUsager(usagerId);

        boolean hasReservations = !reservationRepository.findByUsagerId(usagerId).isEmpty();
        boolean hasEmprunts = !empruntRepository.findByUsagerId(usagerId).isEmpty();

        if (hasReservations) {
            return "Vous ne pouvez pas supprimer cet usager, car il a des réservations en cours.";
        }
        if (hasEmprunts) {
            return "Vous ne pouvez pas supprimer cet usager, car il a des emprunts en cours.";
        }

        usagerRepository.deleteById(usagerId);
        return "Suppression effectuée.";
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
        return livreRepository.findAll(Sort.by(Sort.Direction.ASC, "titre", "auteur"));

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
        return magazineRepository.findAll(Sort.by(Sort.Direction.ASC, "revue", "titre"));

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
    public String supprimerExemplaire(Long exemplaireId) {
        boolean hasEmprunts = !empruntRepository.findByExemplaireId(exemplaireId).isEmpty();
        if (hasEmprunts) {
            return "Vous ne pouvez pas supprimer cet exemplaire, car il est emprunté par un usager.";
        }
        exemplaireRepository.deleteById(exemplaireId);
        return "Suppression effectuée.";
    }
    public void modifierExemplaire(Long exemplaireId, String etat, String disponibilite) {
        Exemplaire exemplaire = obtenirExemplaire(exemplaireId);
        if(etat == "abimé"){
            exemplaireRepository.deleteById(exemplaireId);
        }
        else{
            exemplaire.setEtat(etat);
            exemplaire.setDisponibilite(disponibilite);
            exemplaireRepository.save(exemplaire);
        }


    }









}
