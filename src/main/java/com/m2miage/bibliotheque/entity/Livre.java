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
    //public void supprimer() {}

}
