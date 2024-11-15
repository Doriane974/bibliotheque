package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.*;
import com.m2miage.bibliotheque.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IhmBackOffice {
    @Autowired
    private GestionBackOffice gestionBackOffice;
    //private GestionFrontOffice gestionFrontOffice;

    //Creation entités

    @GetMapping("/creer-usager")
    public String afficherFormulaireCreerUsager() {
        return "creerUsager";
    }

    @PostMapping("/creer-usager")
    public String creerUsager(@RequestParam String nom, @RequestParam String prenom, Model model) {
        String message = gestionBackOffice.creerUsager(nom, prenom);
        model.addAttribute("message", message);
        return "resultatCreation";
    }

    @GetMapping("/creer-oeuvre")
    public String afficherFormulaireCreerOeuvre(){return "creerOeuvre";}



/*    @PostMapping("/creer-oeuvre")
    public String creerOeuvre(@RequestParam String titre, @RequestParam String dateParution, Model model){
        String message = gestionBackOffice.creerOeuvre(titre, dateParution);
        model.addAttribute("message", message);
        return "resultatCreation";
    }*/


    // Afficher le formulaire pour créer un livre
    @GetMapping("/creer-livre")
    public String afficherFormulaireCreerLivre() {
        return "creerLivre";
    }

    // Gérer la soumission du formulaire pour créer un livre
    @PostMapping("/creer-livre")
    public String creerLivre(
            @RequestParam String titre,
            @RequestParam String auteur,
            @RequestParam String edition,
            @RequestParam String dateParution,
            Model model) {

        String message = gestionBackOffice.creerLivre(titre, auteur, edition, dateParution);
        model.addAttribute("message", message);
        return "resultatCreation"; // Page pour afficher le résultat
    }

    // Afficher le formulaire pour créer un livre
    @GetMapping("/creer-magazine")
    public String afficherFormulaireCreerMagazine() {
        return "creerMagazine";
    }

    // Gérer la soumission du formulaire pour créer un livre
    @PostMapping("/creer-magazine")
    public String creerMagazine(
            @RequestParam String titre,
            @RequestParam int numero,
            @RequestParam String revue,
            @RequestParam String dateParution,
            Model model) {

        String message = gestionBackOffice.creerMagazine(titre, revue, numero, dateParution);
        model.addAttribute("message", message);
        return "resultatCreation"; // Page pour afficher le résultat
    }



    //Affichage entités

    @GetMapping("/liste-usagers")
    public String afficherListeUsagers(Model model) {
        List<Usager> usagers = gestionBackOffice.ObtenirTousUsagers();
        model.addAttribute("usagers", usagers);
        return "listeUsagers";
    }

    @GetMapping("/liste-oeuvres")
    public String afficherListeOeuvres(Model model) {
        List<Magazine> magazines = gestionBackOffice.ObtenirTousMagazines();
        List<Livre> livres = gestionBackOffice.ObtenirTousLivres();

        // Validate and handle potential null values
        if (magazines == null) magazines = new ArrayList<>();
        if (livres == null) livres = new ArrayList<>();

        model.addAttribute("magazines", magazines);
        model.addAttribute("livres", livres);

        return "listeOeuvres";
    }


    @GetMapping("/liste-livres")
    public String afficherListeLivres(Model model) {
        List<Livre> livres = gestionBackOffice.ObtenirTousLivres();
        model.addAttribute("livres", livres);
        return "listeLivres";
    }

    @GetMapping("/liste-magazines")
    public String afficherListeMagazines(Model model) {
        List<Magazine> magazines = gestionBackOffice.ObtenirTousMagazines();
        model.addAttribute("magazines", magazines);
        return "listeMagazines";
    }




    @GetMapping("/ajouterExemplaire")
    public String afficherFormulaireAjouterExemplaire(@RequestParam Long oeuvreId, Model model) {
        model.addAttribute("oeuvreId", oeuvreId);
        return "ajouterExemplaire"; // This is the template for the form
    }

    // POST method to handle form submission
    @PostMapping("/ajouterExemplaire")
    public String ajouterExemplaire(@RequestParam Long oeuvreId,
                                    @RequestParam String etat,
                                    @RequestParam String disponibilite,
                                    Model model) {
        String message = gestionBackOffice.ajouterExemplaire(oeuvreId, etat, disponibilite);
        model.addAttribute("message", message);
        return "resultatCreation"; // Reuse the result template
    }

    @GetMapping("/afficherExemplaires")
    public String afficherExemplaires(@RequestParam Long oeuvreId, Model model) {
        List<Exemplaire> exemplaires = gestionBackOffice.obtenirExemplaires(oeuvreId);
        model.addAttribute("exemplaires", exemplaires);
        return "listeExemplaires"; // The template to display exemplaires
    }


    // Delete an exemplaire
    @PostMapping("/supprimerExemplaire")
    public String supprimerExemplaire(@RequestParam Long exemplaireId, @RequestParam Long oeuvreId) {
        gestionBackOffice.supprimerExemplaire(exemplaireId);
        return "redirect:/afficherExemplaires?oeuvreId=" + oeuvreId;
    }

    // Show form to modify an exemplaire
    @GetMapping("/modifierExemplaire")
    public String afficherModifierExemplaireForm(@RequestParam Long exemplaireId, Model model) {
        Exemplaire exemplaire = gestionBackOffice.obtenirExemplaire(exemplaireId);
        model.addAttribute("exemplaire", exemplaire);
        return "modifierExemplaire";
    }

    // Handle form submission for modifications
    @PostMapping("/modifierExemplaire")
    public String modifierExemplaire(@RequestParam Long exemplaireId,
                                     @RequestParam String etat,
                                     @RequestParam String disponibilite,
                                     @RequestParam Long oeuvreId) {
        gestionBackOffice.modifierExemplaire(exemplaireId, etat, disponibilite);
        return "redirect:/afficherExemplaires?oeuvreId=" + oeuvreId;
    }


    // Delete a usager
    @PostMapping("/supprimerUsager")
    public String supprimerUsager(@RequestParam Long usagerId) {
        gestionBackOffice.supprimerUsager(usagerId);
        return "redirect:/liste-usagers";
    }

    // Show form to modify a usager
    @GetMapping("/modifierUsager")
    public String afficherModifierUsagerForm(@RequestParam Long usagerId, Model model) {
        Usager usager = gestionBackOffice.obtenirUsager(usagerId);
        model.addAttribute("usager", usager);
        return "modifierUsager";
    }

    // Handle form submission for modifying a usager
    @PostMapping("/modifierUsager")
    public String modifierUsager(@RequestParam Long usagerId,
                                 @RequestParam String nom,
                                 @RequestParam String prenom) {
        gestionBackOffice.modifierUsager(usagerId, nom, prenom);
        return "redirect:/liste-usagers";
    }


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
        String message = gestionBackOffice.creerReservation(usagerId, oeuvreId);
        model.addAttribute("message", message);

        return "resultatCreation";
    }

    @GetMapping("/liste-reservations")
    public String afficherListeReservations(Model model) {
        List<Reservation> reservations = gestionBackOffice.obtenirTousReservations();
        model.addAttribute("reservations", reservations);
        return "listeReservations";
    }

}
