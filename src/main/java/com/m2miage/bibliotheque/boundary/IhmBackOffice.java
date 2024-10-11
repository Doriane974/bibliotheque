package com.m2miage.bibliotheque.boundary;

import com.m2miage.bibliotheque.control.GestionBackOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IhmBackOffice {
    @Autowired
    private GestionBackOffice gestionBackOffice;

    @GetMapping("/creer-usager")
    public String afficherFormulaire() {
        return "creerUsager";
    }

    @PostMapping("/creer-usager")
    public String creerUsager(@RequestParam String nom, @RequestParam String prenom, Model model) {
        String message = gestionBackOffice.creerUsager(nom, prenom);
        model.addAttribute("message", message);
        return "resultatCreation";
    }


}
