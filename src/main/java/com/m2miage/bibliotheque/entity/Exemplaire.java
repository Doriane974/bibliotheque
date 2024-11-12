package com.m2miage.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat; // "bon" ou "abimé"
    private String disponibilite; // "en rayon" ou "emprunté"

    @ManyToOne // Si Exemplaire est lié à Oeuvre, par exemple
    @JoinColumn(name = "oeuvre_id", nullable = false)
    private Oeuvre oeuvre;


    public Exemplaire() {
        // Constructeur par défaut
    }

    public Exemplaire(String etat, String disponibilite) {
        this.etat = etat;
        this.disponibilite = disponibilite;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void rendre(String etat) {
        // Implémentation de la méthode pour rendre un exemplaire avec un état spécifié
        this.etat = etat;
        this.disponibilite = "en rayon";
    }

    public void ajouter() {
        // Implémentation de la méthode pour ajouter un exemplaire
    }

    public void supprimer() {
        // Implémentation de la méthode pour supprimer un exemplaire
    }

    public Exemplaire identification(Oeuvre o) {
        // Implémentation de la méthode pour identifier un exemplaire par rapport à une oeuvre
        return this;
    }



    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
}
