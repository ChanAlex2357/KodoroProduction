package mg.kodoro.utils;

public class ValidationUtils {
    // Helper to validate positive double values
    public static double validatePositiveStringDouble(String value) {
        double val = Double.parseDouble(value);
        return validatePositiveDouble(val);
    }

    public static double validatePositiveDouble(double d) {
        if (d <= 0) {
            throw new IllegalArgumentException("La valeur doit être supérieure à zéro.");
        }
        return d;
    }

    
}
