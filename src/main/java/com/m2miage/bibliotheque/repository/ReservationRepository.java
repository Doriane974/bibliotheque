package com.m2miage.bibliotheque.repository;

import com.m2miage.bibliotheque.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOeuvreId(Long oeuvreId);
    boolean existsByOeuvreIdAndUsagerId(Long oeuvreId, Long usagerId);
}