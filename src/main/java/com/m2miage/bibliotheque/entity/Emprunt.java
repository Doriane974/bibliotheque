
package com.m2miage.bibliotheque.entity;

import com.m2miage.bibliotheque.Utils.DateJour;
import java.time.LocalDate;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;


@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usager_id", nullable = false)
    private Usager usager;

    @OneToOne
    @JoinColumn(name = "exemplaire_id", nullable = false)
    private Exemplaire exemplaire;

    private LocalDate jourDebut;
    private LocalDate jourFin;
    private boolean archive;


    public Emprunt() {}

    public Emprunt(Usager usager, Exemplaire exemplaire, LocalDate jourDebut) {
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.exemplaire.setDisponibilite("indisponible");
        //annuler une reservation pour cette oeuvre si l'usager qui emprunte avait fait une reservation pour cette oeuvre
        this.jourDebut = jourDebut;
        this.jourFin = DateJour.addDuration(jourDebut, 0,1,0);
        this.archive = false;

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usager getUsager() {
        return usager;
    }
    public void setUsager(Usager usager) {
        this.usager = usager;
    }
    public Exemplaire getExemplaire() {
        return exemplaire;
    }
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
    public LocalDate getJourDebut() {
        return jourDebut;
    }
    public void setJourDebut(LocalDate jourDebut) {
        this.jourDebut = jourDebut;
    }
    public LocalDate getJourFin() {
        return jourFin;
    }
    public void setJourFin(LocalDate jourFin) {
        this.jourFin = jourFin;
    }
    public boolean isArchive() {
        return archive;
    }
    public void setArchive(boolean archive) {
        this.archive = archive;
    }
    public void archiverEmprunt(){
        this.setArchive(true);
    }
}
