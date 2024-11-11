package com.m2miage.bibliotheque.Utils;

public class DateJour {
    int jour;
    int mois;
    int annee;

    public DateJour(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public void printDateJour(){
        System.out.println(this.jour+"/"+this.mois+"/"+this.annee);
    }

    public int calculPeriode(){
        return 0;
    }
}
