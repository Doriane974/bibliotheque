package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOeuvreId(Long oeuvreId);
    Optional<Reservation> findByUsagerAndOeuvre(Usager usager, Oeuvre oeuvre);
    List<Reservation> findByUsagerId(Long usagerId);
    List<Reservation> findAll(Sort sort);
}