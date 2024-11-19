package com.m2miage.bibliotheque.control;

import com.m2miage.bibliotheque.entity.*;
import com.m2miage.bibliotheque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private ReservationRepository reservationRepository;
    @Autowired
    private EmpruntRepository empruntRepository;

    ////////////////////////////////////////
    //      Gestion des réservations      //
    ////////////////////////////////////////

    public String creerReservation(Long usagerId, Long oeuvreId) {
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
        return reservationRepository.findAll(Sort.by(Sort.Direction.ASC, "oeuvre", "usager"));

    }
    public Reservation obtenirReservation(Long reservationID){
        return reservationRepository.findById(reservationID).orElseThrow(() -> new RuntimeException("Emprunt not found"));
    }
    public Optional<Reservation> obtenirReservationParUsagerEtOeuvre(Usager usager, Oeuvre oeuvre) {
        return reservationRepository.findByUsagerAndOeuvre(usager, oeuvre);
    }
    public void supprimerReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
    public void supprimerReservation(Long reservationId){
        Reservation reservationToDelete = obtenirReservation(reservationId);
        supprimerReservation(reservationToDelete);
    }

    ////////////////////////////////////////
    //        Gestion des Emprunts        //
    ////////////////////////////////////////

    public void creerEmprunt(Usager usager, Exemplaire exemplaire) {
        // Set the availability of the exemplaire to "indisponible"
        exemplaire.setDisponibilite("indisponible");

        // Create a new Emprunt object
        Emprunt emprunt = new Emprunt(usager, exemplaire, LocalDate.now());
        empruntRepository.save(emprunt);
    }
    public List<Emprunt> obtenirTousEmprunts(){
        return empruntRepository.findAll(Sort.by(Sort.Direction.ASC,  "usager"));

    }
    public Emprunt obtenirEmprunt(Long empruntId){
        return empruntRepository.findById(empruntId)
                .orElseThrow(() -> new RuntimeException("Emprunt not found"));
    }
    public void supprimerEmprunt(Long empruntId){
        empruntRepository.deleteById(empruntId);
    }

}
