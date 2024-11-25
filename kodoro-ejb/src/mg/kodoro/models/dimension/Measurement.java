package mg.kodoro.models.dimension;

public class Measurement {
    double value;
    String unit;

    public Measurement (String imput) {
        parseMeasurement(imput);
    }

    public void parseMeasurement(String input) {
        // Enlever les espaces inutiles
        input = input.trim();

        // Regex pour extraire le nombre (éventuellement avec décimales) et l'unité
        String regex = "(\\d+\\.?\\d*)\\s*(\\w*)";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        // Valeurs par défaut
        double value = 0;
        String unit = "m";

        if (matcher.matches()) {
            // Extraire la partie numérique
            value = Double.parseDouble(matcher.group(1));

            // Extraire l'unité s'il y en a une; sinon, utiliser l'unité par défaut ("m")
            if (!matcher.group(2).isEmpty()) {
                unit = matcher.group(2).toLowerCase(); // Normaliser en minuscule
            }
        } else {
            throw new IllegalArgumentException("Input format incorrect: " + input);
        }
        setValue(value);
        setUnit(unit);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        System.out.println("VALUE : "+value);
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        System.out.println("UNIT : "+unit);
        this.unit = unit;
    }

    public double getMesureInMeter() {
        return Measurement.getMesureInMeter(value, unit);
    }
    public static double getMesureInMeter(double value, String unit) {
        if (unit.equalsIgnoreCase("m")) {
            return value;
        } else if (unit.equalsIgnoreCase("cm")) {
            return value / 100.0;  // Conversion de cm en m
        } else {
            throw new IllegalArgumentException("VALEUR ENTREE : ("+value+" "+unit+") L'unite de mesure "+unit+" n'est pas une unite valide");
        }
    }
}
