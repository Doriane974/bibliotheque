package com.m2miage.bibliotheque.Utils;

import java.time.LocalDate;
import java.time.Period;

public class DateJour {

    /**
     * Compares deux dates
     * @param date1 La premiere date
     * @param date2 La deuxieme date
     * @return -1 si date1 est avant date2, , 0 si elles sont égales, 1 si date1 est après date2.
     */
    public static int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    /**
     * Calcule la periode de temps entre 2 dates en jours, mois et années
     * @param startDate La date la plus tot
     * @param endDate La date la plus tard
     * @return Un objet Period représentant la periode de temps
     */
    public static Period calculateDuration(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }



    /**
     * Produit une nouvelle date qui est d'une période deonnées apres la date en entrée
     * @param date La date de départ
     * @param days Le nombre de jours à ajouter
     * @param months Le nombre de mois à ajouter
     * @param years Le nombre d'années à ajouter
     * @return La nouvelle date calculée
     */
    public static LocalDate addDuration(LocalDate date, int days, int months, int years) {
        return date.plusYears(years).plusMonths(months).plusDays(days);
    }

}
