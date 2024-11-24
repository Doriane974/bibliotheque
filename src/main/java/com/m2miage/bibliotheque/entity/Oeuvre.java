package com.m2miage.bibliotheque.entity;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Oeuvre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String dateParution;
    private int nbResa;

    public Oeuvre(){}

    public Oeuvre(String titre, String dateParution) {
        this.titre = titre;
        this.dateParution = dateParution;
        this.nbResa = 0;
        //this.nbReservation = 0;
    }

    public int getNbResa() {
        return nbResa;
    }

    public void setNbResa(int nbResa) {
        this.nbResa = nbResa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateParution() {
        return dateParution;
    }

    public void setDateParution(String dateParution) {
        this.dateParution = dateParution;
    }

    public void ajouterReservation(){
        this.setNbResa(this.getNbResa()+1);
    }

    public void enleverReservation(){
        this.setNbResa(this.getNbResa()-1);
    }
}
