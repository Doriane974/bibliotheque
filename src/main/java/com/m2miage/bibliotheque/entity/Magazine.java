package com.m2miage.bibliotheque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Magazine extends Oeuvre {

    private String revue;
    private int numero;

    public Magazine() {
        super();
    }
    public Magazine(String titre, String revue, int numero, String dateParution) {
        super(titre, dateParution);
        this.revue = revue;
        this.numero = numero;
    }

}
