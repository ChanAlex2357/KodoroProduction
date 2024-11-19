package mg.kodoro.helpers.seeds;

public class MargeSeeds extends IntegerSeeds {
    public MargeSeeds(double marge){
        super(0, marge);
    }
    public MargeSeeds(String marge){
        super("0", marge);
    }
    public double generatePourcentage(){
        double randMarge = generate();
        System.out.println(randMarge);
        double pourcentage = randMarge / 100;
        System.out.println(randMarge/100);
        return pourcentage;
    }
}
