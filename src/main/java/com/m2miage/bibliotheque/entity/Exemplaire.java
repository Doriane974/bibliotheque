package com.m2miage.bibliotheque.entity;

import jakarta.persistence.*;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat;
    private String disponibilite;

    @ManyToOne
    @JoinColumn(name = "oeuvre_id", nullable = false)
    private Oeuvre oeuvre;


    public Exemplaire() {}

    public Exemplaire(String etat, String disponibilite) {
        this.etat = etat;
        this.disponibilite = disponibilite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
