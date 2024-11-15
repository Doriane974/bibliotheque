package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.*;
import com.m2miage.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GestionFrontOffice {
    @Autowired
    private UsagerRepository usagerRepository;
    @Autowired
    private OeuvreRepository oeuvreRepository;
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

 /*   public String creerReservation(Long usagerId, Long oeuvreId) {
        Usager usager = usagerRepository.findById(usagerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Oeuvre oeuvre = oeuvreRepository.findById(oeuvreId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid oeuvre ID"));

        // Check if a reservation already exists for this oeuvre and usager
        Optional<Reservation> existingReservation = reservationRepository
                .findByUsagerAndOeuvre(usager, oeuvre);
        if (existingReservation.isPresent()) {
            return "Reservation already exists for this usager and oeuvre";
        }

        // Create new reservation
        Reservation reservation = new Reservation();
        reservation.setUsager(usager);
        reservation.setOeuvre(oeuvre);
        reservation.setDate(LocalDate.now());

        reservationRepository.save(reservation);

        return "Reservation réalisée avec succès";
    }

    public List<Reservation> obtenirTousReservations() {
        return reservationRepository.findAll();
    }*/

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
 /*   public String creerLivre(String titre, String auteur, String edition, String dateParution) {
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


    public void supprimerUsager(Long usagerId) {
        usagerRepository.deleteById(usagerId);
    }

    public Usager obtenirUsager(Long usagerId) {
        return usagerRepository.findById(usagerId)
                .orElseThrow(() -> new RuntimeException("Usager not found"));
    }

    public void modifierUsager(Long usagerId, String nom, String prenom) {
        Usager usager = obtenirUsager(usagerId);
        if (!nom.isBlank()) usager.setNom(nom);
        if (!prenom.isBlank()) usager.setPrenom(prenom);
        usagerRepository.save(usager);
    }

*/

}
