package com.m2miage.bibliotheque.entity;
import com.m2miage.bibliotheque.Utils.DateJour;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import java.time.LocalDate;

@Entity
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"oeuvre_id", "usager_id"})
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "oeuvre_id", nullable = false)
    private Oeuvre oeuvre;

    @ManyToOne
    @JoinColumn(name = "usager_id", nullable = false)
    private Usager usager;

    private LocalDate date;

    public Reservation() {}
    public Reservation(Usager usager, Oeuvre oeuvre, LocalDate date){
        this.usager=usager;
        this.oeuvre=oeuvre;
        this.date = date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }





}

