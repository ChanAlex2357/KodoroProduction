package mg.kodoro.helpers.seeds;


public abstract class IntervalleSeeds<T> {
    private double min;
    private double max;
    // Constructeur
    public IntervalleSeeds(double min, double max) {
        setMin(min);
        setMax(max);
    }
    public IntervalleSeeds(String min, String max) {
        setMin(Double.parseDouble(min));
        setMax(Double.parseDouble(max));
    }

    public double getMin() {
        return min;
    }
    public int getIntMin(){
        return (int) getMin();
    }

    public void setMin(double min) {
        this.min = min;
    }


    public double getMax() {
        return max;
    }
    public int getIntMax(){
        return (int) getMax();
    }

    public void setMax(double max) {
        this.max = max;
    }

    public abstract T generate();
}

