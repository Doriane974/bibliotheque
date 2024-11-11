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

    public Oeuvre(){}

    public Oeuvre(String titre, String dateParution) {
        this.titre = titre;
        this.dateParution = dateParution;
        //this.nbReservation = 0;
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

    /*public int getNbReservation() {
        return nbReservation;
    }*/

    /*public void setNbReservation(int nbReservation) {
        this.nbReservation = nbReservation;
    }*/

    public void supprimer(){
        /*if(nbReservation == 0){
            //Supprimer tous les exemplaires
        }
        else{
            System.out.println("On ne peut pas supprimer cette oeuvre : un exemplaire est encore emprunté par un usager.");
        }
        //supprimer tous les exemplaires associé a cette oeuvre, seulement si nbReservaton est egal a 0
        */
    }


    static Oeuvre identification(String titre){
        //if(this.titre == titre){
        //    return this;
        //}
        return new Oeuvre();
    }

    static boolean estDisponible(){
        //return nbReservation<nbExemplaire && nbExemplaire>0;
        return false;
    }
}
