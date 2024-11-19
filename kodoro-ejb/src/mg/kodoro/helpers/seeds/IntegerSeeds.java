package mg.kodoro.helpers.seeds;

import java.util.Random;

public class IntegerSeeds extends IntervalleSeeds<Integer>{
    public IntegerSeeds (double min , double max) {
        super(min, max);
    }
    @Override
    public Integer generate() {
        int min = getIntMin();
        int max = getIntMax();
        if (min > max) {
            throw new IllegalArgumentException("Le minimum ne peut pas être supérieur au maximum.");
        }
        Random random = new Random();
        return random.nextInt( (max - min + 1)) +  min;
    }
    
}
