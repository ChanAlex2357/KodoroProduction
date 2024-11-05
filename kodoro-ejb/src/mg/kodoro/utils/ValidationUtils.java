package mg.kodoro.utils;

public class ValidationUtils {
    // Helper to validate positive double values
    public static double validatePositiveDouble(String value) {
        double val = Double.parseDouble(value);
        if (val <= 0) {
            throw new IllegalArgumentException("La valeur doit être supérieure à zéro.");
        }
        return val;
    }
}
