package mg.kodoro.utils;

public class ValidationUtils {
    // Helper to validate positive double values
    public static double validatePositiveStringDouble(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("La valeur fournie est nulle ou vide");
        }
        // Supprimer tous les espaces dans la chaîne
        String cleanedValue = value.replaceAll("\\s+", "");
        double val = Double.parseDouble(cleanedValue);
        return validatePositiveDouble(val);
    }
    

    public static double validatePositiveDouble(double d) {
        if (d <= 0) {
            throw new IllegalArgumentException("La valeur doit être supérieure à zéro.");
        }
        return d;
    }

    public static int validatePositiveStringInt(String quantite) {
        int val  = Integer.parseInt(quantite);
        return validatePositiveStringInt(val);
    }
        
    public static int validatePositiveStringInt(int val) {
        validatePositiveDouble(val);
        return val;    
    }
    
}
