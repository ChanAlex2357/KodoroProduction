package mg.kodoro.helpers.seeds;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.sql.Date;

public class DateSeeds extends IntervalleSeeds<Date> {

    private final Random random;

    public DateSeeds(String min, String max) {
        super(min, max);
        this.random = new Random();
    }

    public DateSeeds(double annee_min, double annee_max) {
        super(annee_min, annee_max);
        this.random = new Random();
    }

    /**
     * Génère une date aléatoire entre les années spécifiées.
     *
     * @return Une instance de java.sql.Date représentant la date aléatoire.
     */
    @Override
    public Date generate() {
        int minYear = (int) getMin();
        int maxYear = (int) getMax();

        if (minYear > maxYear) {
            throw new IllegalArgumentException("L'année minimale ne peut pas être supérieure à l'année maximale.");
        }

        LocalDate startDate = LocalDate.of(minYear, 1, 1);
        LocalDate endDate = LocalDate.of(maxYear, 12, 31);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // Générer un nombre aléatoire entre 0 et daysBetween
        long randomDays = (long) (random.nextDouble() * (daysBetween + 1));

        LocalDate randomDate = startDate.plusDays(randomDays);

        return Date.valueOf(randomDate);
    }


    /**
     * Génère une date aléatoire qui est un jour ouvrable.
     *
     * @return Une instance de java.sql.Date représentant une date ouvrable aléatoire.
     */
    public Date generateWorkDate() {
        Date daty;
        LocalDate localDate;

        do {
            daty = generate();
            localDate = daty.toLocalDate();
        } while (isWeekend(localDate));

        return daty;
    }

    /**
     * Vérifie si une date est un week-end.
     *
     * @param date La date à vérifier.
     * @return true si la date est un samedi ou un dimanche, sinon false.
     */
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7;
    }
}
