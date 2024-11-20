package mg.kodoro.models.blockggen;

public class DataBloc {
    double LMin;
    double LMax;
    double lMin;
    double lMax;
    double eMin;
    double eMax;
    double aMin;
    double aMax;
    double marge;
    public  DataBloc(){
        // LONG
        setLMin(5);
        setLMax(7);
        // LARGE
        setlMin(20);
        setlMax(25);
        // EP
        seteMin(10);
        seteMax(15);
        // ANNEE
        setaMin(2022);
        setaMax(2024);

        setMarge(10);
    }
    public DataBloc( double LMin, double LMax, double lMin, double lMax,
    double eMin, double eMax, double aMin, double aMax, double marge){
        setLMin(LMin);
        setLMax(LMax);
        setlMin(lMin);
        setlMax(lMax);
        seteMin(eMin);
        seteMax(eMax);
        setaMin(aMin);
        setaMax(aMax);
        setMarge(marge);
    }
    public DataBloc( String LMin, String LMax, String lMin, String lMax,
    String eMin, String eMax, String aMin, String aMax, String marge){
        setLMin(LMin);
        setLMax(LMax);
        setlMin(lMin);
        setlMax(lMax);
        seteMin(eMin);
        seteMax(eMax);
        setaMin(aMin);
        setaMax(aMax);
        setMarge(marge);
    }

    public double getLMin() {
        return LMin;
    }
    public void setLMin(double lMin) {
        LMin = lMin;
    }
    public void setLMin(String lMin) {
        LMin = Integer.parseInt(lMin);
    }
    public double getLMax() {
        return LMax;
    }
    public void setLMax(double lMax) {
        LMax = lMax;
    }
    public void setLMax(String lMax) {
        LMax = Integer.parseInt(lMax);
    }
    public double getlMin() {
        return lMin;
    }
    public void setlMin(double lMin) {
        this.lMin = lMin;
    }
    public void setlMin(String lMin) {
        this.lMin = Integer.parseInt(lMin);
    }
    public double getlMax() {
        return lMax;
    }
    public void setlMax(double lMax) {
        this.lMax = lMax;
    }
    public void setlMax(String lMax) {
        this.lMax = Integer.parseInt(lMax);
    }
    public double geteMin() {
        return eMin;
    }
    public void seteMin(double eMin) {
        this.eMin = eMin;
    }
    public void seteMin(String eMin) {
        this.eMin = Integer.parseInt(eMin);
    }


    public double geteMax() {
        return eMax;
    }


    public void seteMax(double eMax) {
        this.eMax = eMax;
    }
    public void seteMax(String eMax) {
        this.eMax = Integer.parseInt(eMax);
    }


    public double getaMin() {
        return aMin;
    }


    public void setaMin(double aMin) {
        this.aMin = aMin;
    }
    public void setaMin(String aMin) {
        this.aMin = Integer.parseInt(aMin);
    }


    public double getaMax() {
        return aMax;
    }


    public void setaMax(double aMax) {
        this.aMax = aMax;
    }
    public void setaMax(String aMax) {
        this.aMax = Integer.parseInt(aMax);
    }


    public double getMarge() {
        return marge;
    }


    public void setMarge(double marge) {
        this.marge = marge;
    }
    public void setMarge(String marge) {
        this.marge = Integer.parseInt(marge);
    }
}
