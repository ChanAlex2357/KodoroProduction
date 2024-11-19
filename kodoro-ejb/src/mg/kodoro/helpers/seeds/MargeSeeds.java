package mg.kodoro.helpers.seeds;

public class MargeSeeds extends IntegerSeeds {
    public MargeSeeds(double marge){
        super(0, marge);
    }
    public MargeSeeds(String marge){
        super("0", marge);
    }
    public double generatePourcentage(){
        int randMarge = generate();
        return randMarge / 100;
    }
}
