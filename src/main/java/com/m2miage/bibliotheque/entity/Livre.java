package com.m2miage.bibliotheque.entity;

import jakarta.persistence.Entity;


@Entity
public class Livre extends Oeuvre {

    private String edition;
    private String auteur;

    public Livre() {
        super();
    }

    public Livre(String titre, String dateParution, String auteur, String edition) {
        super(titre, dateParution);
        this.auteur = auteur;
        this.edition = edition;
    }
    public void supprimer() {
        // Implémentation de la méthode pour supprimer un livre
    }

    // Getters et Setters
    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
