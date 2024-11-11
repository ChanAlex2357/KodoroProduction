package mg.kodoro;

public class MeasureParser {
    public static void main(String[] args) {
        System.out.println(parseMeasurement("10m"));      // Output: 10.0 m
        System.out.println(parseMeasurement("10 m"));     // Output: 10.0 m
        System.out.println(parseMeasurement("10cm"));     // Output: 10.0 cm
        System.out.println(parseMeasurement(" 10 cm "));  // Output: 10.0 cm
        System.out.println(parseMeasurement("10"));       // Output: 10.0 m
    }

   
}
