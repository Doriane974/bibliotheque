package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Reservation;
import com.m2miage.bibliotheque.entity.Oeuvre;
import com.m2miage.bibliotheque.entity.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOeuvreId(Long oeuvreId);
    Optional<Reservation> findByUsagerAndOeuvre(Usager usager, Oeuvre oeuvre);
}