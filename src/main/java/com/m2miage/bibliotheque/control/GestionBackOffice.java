package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.Oeuvre;
import com.m2miage.bibliotheque.entity.Usager;
import com.m2miage.bibliotheque.entity.Livre;
import com.m2miage.bibliotheque.entity.Magazine;
import com.m2miage.bibliotheque.entity.Exemplaire;
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

    public String creerUsager(String nom, String prenom) {
        if (usagerRepository.existsByNomAndPrenom(nom, prenom)) {
            return "L'usager existe déjà.";
        } else {
            Usager newUsager = new Usager(nom, prenom);
            usagerRepository.save(newUsager);
            return "Usager créé avec succès.";
        }
    }

/*    public String creerOeuvre(String titre, String dateparution){
        if(oeuvreRepository.existsByTitreAndDateParution(titre, dateparution)){
            return "L'oeuvre est deja enregistrée.";
        }else{
            Oeuvre newOeuvre = new Oeuvre(titre, dateparution);
            oeuvreRepository.save(newOeuvre);
            return "Oeuvre créée avec succès.";
        }
    }*/


    // Méthode pour créer un nouveau livre
    public String creerLivre(String titre, String auteur, String edition, String dateParution) {
        Livre livre = new Livre(titre, dateParution, auteur, edition);
        livreRepository.save(livre);
        return "Livre créé avec succès.";
    }

    public String creerMagazine(String titre, String revue, int numero, String dateParution) {
        Magazine magazine = new Magazine(titre, revue, numero, dateParution);
        magazineRepository.save(magazine);
        return "Magazine créé avec succès.";
    }



    public List<Usager> ObtenirTousUsagers() {
        return usagerRepository.findAll();
    }

    public List<Livre> ObtenirTousLivres() {
        return livreRepository.findAll();
    }

    public List<Magazine> ObtenirTousMagazines() {
        return magazineRepository.findAll();
    }

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

    public List<Exemplaire> obtenirExemplaires(Long oeuvreId) {
        return exemplaireRepository.findByOeuvreId(oeuvreId);
    }

    public void supprimerExemplaire(Long exemplaireId) {
        exemplaireRepository.deleteById(exemplaireId);
    }

    public Exemplaire obtenirExemplaire(Long exemplaireId) {
        return exemplaireRepository.findById(exemplaireId)
                .orElseThrow(() -> new RuntimeException("Exemplaire not found"));
    }

    public void modifierExemplaire(Long exemplaireId, String etat, String disponibilite) {
        Exemplaire exemplaire = obtenirExemplaire(exemplaireId);
        exemplaire.setEtat(etat);
        exemplaire.setDisponibilite(disponibilite);
        exemplaireRepository.save(exemplaire);
    }


}
