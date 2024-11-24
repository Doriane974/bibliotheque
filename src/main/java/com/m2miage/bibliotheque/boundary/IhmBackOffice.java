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

    @GetMapping("/index-back-office")
    public String afficherMenuBackOffice() {
        return "indexBackOffice";
    }

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
    public String supprimerUsager(@RequestParam Long usagerId, Model model) {
        String message = gestionBackOffice.supprimerUsager(usagerId);
        model.addAttribute("message", message);

        if ("Suppression effectuée.".equals(message)) {
            return "redirect:/liste-usagers";
        }
        return "resultatCreation";
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
        return "resultatCreation";
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
        return "resultatCreation";
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
        return "ajouterExemplaire";
    }

    @PostMapping("/ajouterExemplaire")
    public String ajouterExemplaire(@RequestParam Long oeuvreId,
                                    @RequestParam String etat,
                                    @RequestParam String disponibilite,
                                    Model model) {
        String message = gestionBackOffice.ajouterExemplaire(oeuvreId, etat, disponibilite);
        model.addAttribute("message", message);
        return "resultatCreation";
    }

    @GetMapping("/afficherExemplaires")
    public String afficherExemplaires(@RequestParam Long oeuvreId, Model model) {
        List<Exemplaire> exemplaires = gestionBackOffice.obtenirTousExemplaires(oeuvreId);
        model.addAttribute("exemplaires", exemplaires);
        return "listeExemplaires";
    }

    @PostMapping("/supprimerExemplaire")
    public String supprimerExemplaire(@RequestParam Long exemplaireId, @RequestParam Long oeuvreId, Model model) {
        String message = gestionBackOffice.supprimerExemplaire(exemplaireId);
        model.addAttribute("message", message);
        if ("Suppression effectuée.".equals(message)) {
            return "redirect:/afficherExemplaires?oeuvreId=";
        }
        return "resultatCreation";
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
* faire du reverse, regarder les differences, update visual paradigm en conséquence
* gere les dates de facon constante
 */