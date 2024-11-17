package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.*;
import com.m2miage.bibliotheque.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class IhmBackOffice {
    @Autowired
    private GestionBackOffice gestionBackOffice;

    /////////////////////////////////////////
    //               Usagers               //
    /////////////////////////////////////////

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

    @GetMapping("/liste-usagers")
    public String afficherListeUsagers(Model model) {
        List<Usager> usagers = gestionBackOffice.ObtenirTousUsagers();
        model.addAttribute("usagers", usagers);
        return "listeUsagers";
    }

    @PostMapping("/supprimerUsager")
    public String supprimerUsager(@RequestParam Long usagerId) {
        gestionBackOffice.supprimerUsager(usagerId);
        return "redirect:/liste-usagers";
    }

    @GetMapping("/modifierUsager")
    public String afficherModifierUsagerForm(@RequestParam Long usagerId, Model model) {
        Usager usager = gestionBackOffice.obtenirUsager(usagerId);
        model.addAttribute("usager", usager);
        return "modifierUsager";
    }

    @PostMapping("/modifierUsager")
    public String modifierUsager(@RequestParam Long usagerId,
                                 @RequestParam String nom,
                                 @RequestParam String prenom) {
        gestionBackOffice.modifierUsager(usagerId, nom, prenom);
        return "redirect:/liste-usagers";
    }

    /////////////////////////////////////////
    //               Oeuvres               //
    /////////////////////////////////////////

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

    //-------------------------------------//
    //               Livres                //
    //-------------------------------------//

    @GetMapping("/creer-livre")
    public String afficherFormulaireCreerLivre() {
        return "creerLivre";
    }

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

    @GetMapping("/liste-livres")
    public String afficherListeLivres(Model model) {
        List<Livre> livres = gestionBackOffice.ObtenirTousLivres();
        model.addAttribute("livres", livres);
        return "listeLivres";
    }


    //-------------------------------------//
    //              Magazines              //
    //-------------------------------------//

    @GetMapping("/creer-magazine")
    public String afficherFormulaireCreerMagazine() {
        return "creerMagazine";
    }

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

    @GetMapping("/liste-magazines")
    public String afficherListeMagazines(Model model) {
        List<Magazine> magazines = gestionBackOffice.ObtenirTousMagazines();
        model.addAttribute("magazines", magazines);
        return "listeMagazines";
    }

    //-------------------------------------//
    //             Exemplaires             //
    //-------------------------------------//

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
        List<Exemplaire> exemplaires = gestionBackOffice.obtenirTousExemplaires(oeuvreId);
        model.addAttribute("exemplaires", exemplaires);
        return "listeExemplaires"; // The template to display exemplaires
    }

    @PostMapping("/supprimerExemplaire")
    public String supprimerExemplaire(@RequestParam Long exemplaireId, @RequestParam Long oeuvreId) {
        gestionBackOffice.supprimerExemplaire(exemplaireId);
        return "redirect:/afficherExemplaires?oeuvreId=" + oeuvreId;
    }

    @GetMapping("/modifierExemplaire")
    public String afficherModifierExemplaireForm(@RequestParam Long exemplaireId, Model model) {
        Exemplaire exemplaire = gestionBackOffice.obtenirExemplaire(exemplaireId);
        model.addAttribute("exemplaire", exemplaire);
        return "modifierExemplaire";
    }

    @PostMapping("/modifierExemplaire")
    public String modifierExemplaire(@RequestParam Long exemplaireId,
                                     @RequestParam String etat,
                                     @RequestParam String disponibilite,
                                     @RequestParam Long oeuvreId) {
        gestionBackOffice.modifierExemplaire(exemplaireId, etat, disponibilite);
        return "redirect:/afficherExemplaires?oeuvreId=" + oeuvreId;
    }

}


/* TODO :
* Partager les fichiers back/front, usager, oeuvre, emprunt, reservations
* Gerer le rendu des oeuvres : done
* gerer archivage/non archivage des emprunts
* faire du reverse, regarder les differences, update visual paradigm en conséquence
* regarder les endroits ou il manque de la mise en forme (ajouter un exemplaire par exemple)
* ajouter un menu gerer le back/front au debut
* gere les dates de facon constante
 */