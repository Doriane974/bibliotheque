package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.GestionBackOffice;
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




}
