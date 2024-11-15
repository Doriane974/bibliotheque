package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.GestionFrontOffice;
import com.m2miage.bibliotheque.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IhmFrontOffice {
    @Autowired
    private GestionFrontOffice gestionFrontOffice;

    //Creation entités

   /* @GetMapping("/creer-emprunt")
    public String afficherFormulaireCreerEmprunt() {
        return "creerEmprunt";
    }
    @PostMapping("/creer-emprunt")
    public String creerEmprunt(@RequestParam Long usagerID, @RequestParam Long oeuvreId, Model model) {
        String message = gestionFrontOffice.creerEmprunt(usagerID, oeuvreId);
        model.addAttribute("message", message);
        return "resultatCreation";
    }
    @GetMapping("/liste-emprunts")
    public String afficherListeEmprunts(Model model) {
        List<Emprunt> emprunts = gestionFrontOffice.ObtenirTousEmprunts();
        model.addAttribute("emprunts", emprunts);
        return "listeEmprunts";
    }
    // Delete an emprunt
    @PostMapping("/supprimerEmprunt")
    public String supprimerEmprunt(@RequestParam Long empruntId) {
        gestionFrontOffice.supprimerEmprunt(empruntId);
        return "listeEmprunts";
    }
    // Show form to modify an exemplaire
    @GetMapping("/modifierEmprunt")
    public String afficherModifierEmpruntForm(@RequestParam Long empruntId, Model model) {
        Emprunt emprunt = gestionFrontOffice.obtenirEmprunt(empruntId);
        model.addAttribute("emprunt", emprunt);
        return "modifierEmprunt";
    }
    // Handle form submission for modifications
    @PostMapping("/modifierEmprunt")
    public String modifierEmprunt(@RequestParam Long empruntId) {
        //gestionFrontOffice.modifierEmprunt(exemplaireId, etat, disponibilite);
        return "listeEmprunts";
    }
*/

/*    @GetMapping("/creer-reservation")
    public String afficherFormulaireCreerReservation() {
        return "creerReservation";
    }
    @PostMapping("/creer-reservation")
    public String creerReservation(@RequestParam Long usagerID, @RequestParam Long oeuvreId, Model model) {
        String message = gestionFrontOffice.creerReservation(usagerID, oeuvreId);
        model.addAttribute("message", message);
        return "resultatCreation";
    }
    @GetMapping("/liste-reservations")
    public String afficherListeReservations(Model model) {
        List<Reservation> reservations = gestionFrontOffice.obtenirTousReservations();
        model.addAttribute("reservations", reservations);
        return "listeReservations";
    }*/
    /*// Delete a reservation
    @PostMapping("/supprimerReservation")
    public String supprimerReservation(@RequestParam Long reservationId) {
        gestionFrontOffice.supprimerReservation(reservationId);
        return "listeReservations";
    }
    // Show form to modify a reservation
    @GetMapping("/modifierReservation")
    public String afficherModifierReservationForm(@RequestParam Long reservationId, Model model) {
        Reservation reservation = gestionFrontOffice.obtenirReservation(reservationId);
        model.addAttribute("reservation", reservation);
        return "modifierReservation";
    }
    // Handle form submission for modifications
    @PostMapping("/modifierReservation")
    public String modifierReservation(@RequestParam Long reservationId) {
        //gestionFrontOffice.modifierReservation(reservationId);
        return "listeReservations";
    }
    // Delete a usager
    @PostMapping("/supprimerReservation")
    public String supprimerReservation(@RequestParam Long reservationId) {
        gestionFrontOffice.supprimerReservation(reservationId);
        return "listeReservations";
    }
*/
}
