package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.GestionBackOffice;
import com.m2miage.bibliotheque.control.GestionFrontOffice;
import com.m2miage.bibliotheque.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class IhmFrontOffice {
    @Autowired
    private GestionFrontOffice gestionFrontOffice;
    @Autowired
    private GestionBackOffice gestionBackOffice;


    @GetMapping("/index-front-office")
    public String afficherMenuFrontOffice() {
        return "indexFrontOffice";
    }

//////////////////////////////////////////////////////////////////////
//                           Reservations                           //
//////////////////////////////////////////////////////////////////////

    @GetMapping("/creerReservationChoisirUsager")
    public String creerReservationChoisirUsager(Model model) {
        // Fetch all users to be displayed on the initial page
        List<Usager> usagers = gestionBackOffice.ObtenirTousUsagers();
        model.addAttribute("usagers", usagers);
        return "creerReservationChoisirUsager";
    }

    @PostMapping("/creerReservationChoisirUsager")
    public String creerReservationUsagerChoisi(@RequestParam Long usagerId, Model model) {
        // Store the selected user ID and pass it to the next page for selecting the "oeuvre"
        model.addAttribute("selectedUsagerId", usagerId);
        List<Magazine> magazines = gestionBackOffice.ObtenirTousMagazines();
        List<Livre> livres = gestionBackOffice.ObtenirTousLivres();

        // Handle null values if the repositories return null
        if (magazines == null) magazines = new ArrayList<>();
        if (livres == null) livres = new ArrayList<>();

        model.addAttribute("magazines", magazines);
        model.addAttribute("livres", livres);

        return "creerReservationChoisirOeuvre";
    }

    @PostMapping("/creerReservationChoisirOeuvre")
    public String creerReservationOeuvreChoisie(
            @RequestParam("usagerId") Long usagerId,
            @RequestParam("oeuvreId") Long oeuvreId,
            Model model) {

        // Create the reservation and provide feedback
        String message = gestionFrontOffice.creerReservation(usagerId, oeuvreId);
        model.addAttribute("message", message);

        return "resultatCreation";
    }

    @GetMapping("/liste-reservations")
    public String afficherListeReservations(Model model) {
        List<Reservation> reservations = gestionFrontOffice.obtenirTousReservations();
        model.addAttribute("reservations", reservations);
        return "listeReservations";
    }

    @PostMapping("/annulerReservation")
    public String annulerReservation(@RequestParam Long reservationId) {
        gestionFrontOffice.supprimerReservation(reservationId);
        return "redirect:/liste-reservations";
    }

//////////////////////////////////////////////////////////////////////
//                             Emprunts                             //
//////////////////////////////////////////////////////////////////////

    @GetMapping("/creerEmpruntChoisirUsager")
    public String afficherListeUsagersPourEmprunt(Model model) {
        List<Usager> usagers = gestionBackOffice.ObtenirTousUsagers();
        model.addAttribute("usagers", usagers);
        return "creerEmpruntChoisirUsager";  // This should match the name of your HTML file
    }

    @PostMapping("/creerEmpruntChoisirUsager")
    public String creerEmprunUsagerChoisi(@RequestParam("usagerId") Long usagerId, Model model) {
        // Store the selected user ID in the model for later use
        model.addAttribute("selectedUsagerId", usagerId);

        // Fetch all available oeuvres
        List<Oeuvre> oeuvres = gestionBackOffice.obtenirToutesOeuvres();

        // Create a map to store availability for each oeuvre
        Map<Long, Boolean> disponibilites = new HashMap<>();

        // Determine availability for each oeuvre by checking the related exemplaires
        for (Oeuvre oeuvre : oeuvres) {
            List<Exemplaire> exemplaires = gestionBackOffice.obtenirTousExemplaires(oeuvre.getId());

            // Check if there's at least one exemplaire available ("en rayon")
            boolean isDisponible = exemplaires.stream()
                    .anyMatch(exemplaire -> exemplaire.getDisponibilite().equalsIgnoreCase("disponible"));

            disponibilites.put(oeuvre.getId(), isDisponible);
        }

        // Add oeuvres and their availability to the model
        model.addAttribute("oeuvres", oeuvres);
        model.addAttribute("disponibilites", disponibilites); // Map of oeuvreId -> availability
        return "creerEmpruntChoisirOeuvre";  // Forward to the next page to select an oeuvre
    }

    @PostMapping("/creerEmpruntChoisirOeuvre")
    public String creerEmpruntOeuvreChoisie(
            @RequestParam("usagerId") Long usagerId,
            @RequestParam("oeuvreId") Long oeuvreId,
            Model model) {

        Usager usager = gestionBackOffice.obtenirUsager(usagerId);
        Oeuvre oeuvre = gestionBackOffice.obtenirOeuvre(oeuvreId);

        // Find the first available exemplaire
        List<Exemplaire> exemplaires = gestionBackOffice.obtenirTousExemplaires(oeuvre.getId());
        Exemplaire exemplaire = exemplaires.stream()
                .filter(e -> e.getDisponibilite().equals("disponible"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available exemplaire for selected oeuvre"));

        // Create the emprunt
        gestionFrontOffice.creerEmprunt(usager, exemplaire);

        // Remove the reservation if it exists for this usager and oeuvre
        Optional<Reservation> reservation = gestionFrontOffice.obtenirReservationParUsagerEtOeuvre(usager, oeuvre);
        reservation.ifPresent(gestionFrontOffice::supprimerReservation);

        model.addAttribute("message", "Emprunt créé avec succès!");
        return "resultatCreation";  // Display the result
    }

    @GetMapping("/liste-emprunts")
    public String afficherListeEmprunts(Model model) {
        List<Emprunt> emprunts = gestionFrontOffice.obtenirTousEmprunts();
        model.addAttribute("emprunts", emprunts);
        return "listeEmprunts";
    }

    @PostMapping("/rendreExemplaire")
    public String rendreExemplaireInfo(@RequestParam Long empruntId, @RequestParam String etat) {
        Emprunt emprunt = gestionFrontOffice.obtenirEmprunt(empruntId);
        gestionBackOffice.modifierExemplaire(emprunt.getExemplaire().getId(), etat, "disponible");
        gestionFrontOffice.supprimerEmprunt(empruntId);
        return "redirect:/liste-emprunts";
    }
}
