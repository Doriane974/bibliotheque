package com.m2miage.bibliotheque.Utils;

import java.time.LocalDate;
import java.time.Period;

public class DateJour {

    /**
     * Compares two dates.
     * @param date1 The first date.
     * @param date2 The second date.
     * @return -1 if date1 is before date2, 0 if they are equal, 1 if date1 is after date2.
     */
    public static int compareDates(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2);
    }

    /**
     * Calculates the time duration between two dates in terms of days, months, and years.
     * @param startDate The starting date.
     * @param endDate The ending date.
     * @return A Period object representing the duration.
     */
    public static Period calculateDuration(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    /**
     * Calculates the total number of days between two dates manually.
     * @param startDate The starting date.
     * @param endDate The ending date.
     * @return The total number of days between startDate and endDate.
     */
    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        Period period = calculateDuration(startDate, endDate);
        // Convert the years and months into days and add the remaining days
        int totalDays = (period.getYears() * 365) + (period.getMonths() * 30) + period.getDays();
        return Math.abs(totalDays); // Ensure positive number of days
    }

    /**
     * Produces a new date that is a specified duration after a given date.
     * @param date The starting date.
     * @param days The number of days to add.
     * @param months The number of months to add.
     * @param years The number of years to add.
     * @return The new calculated date.
     */
    public static LocalDate addDuration(LocalDate date, int days, int months, int years) {
        return date.plusYears(years).plusMonths(months).plusDays(days);
    }

    public static void main(String[] args) {
        // Example usage:
        LocalDate today = LocalDate.now();
        LocalDate anotherDate = LocalDate.of(2024, 12, 25);

        // Compare dates
        System.out.println("Compare Dates: " + compareDates(today, anotherDate));

        // Calculate duration
        Period duration = calculateDuration(today, anotherDate);
        System.out.println("Duration: " + duration.getYears() + " years, " +
                duration.getMonths() + " months, " + duration.getDays() + " days");

        // Calculate days between
        long daysBetween = calculateDaysBetween(today, anotherDate);
        System.out.println("Days Between: " + daysBetween);

        // Add duration
        LocalDate newDate = addDuration(today, 10, 2, 1);
        System.out.println("New Date: " + newDate);
    }
}
