package mg.kodoro.utils.blockggen;

import java.sql.Connection;
import java.sql.Date;


import mg.kodoro.helpers.seeds.DateSeeds;
import mg.kodoro.helpers.seeds.IntegerSeeds;
import mg.kodoro.helpers.seeds.MargeSeeds;
import mg.kodoro.models.Bloc;
import mg.kodoro.models.blockggen.DataBloc;

public class GenerateurBloc {
    private DataBloc dataBloc;
    

    private IntegerSeeds intervalleLongueur;
    private IntegerSeeds intervalleLargeur;
    private IntegerSeeds intervalleEpaisseur;
    private MargeSeeds   margeSeeds;
    private DateSeeds intervalleAnnee;
    protected  BlocStat blocStat;
    
    public  BlocStat getBlocStat(Connection conn) throws Exception {
        if (blocStat == null) {
            setBlocStat(BlocStat.getBlocStat(conn));
        }
        return blocStat;
    }

    protected  void setBlocStat(BlocStat blocStat) {
        this.blocStat = blocStat;
    }

    public GenerateurBloc(DataBloc dataBloc) {
        setDataBloc(dataBloc);
        setIntervalleLongueur(new IntegerSeeds(dataBloc.getLMin(), dataBloc.getLMax()));
        setIntervalleLargeur(new IntegerSeeds(dataBloc.getlMin(), dataBloc.getlMax()));
        setIntervalleEpaisseur(new IntegerSeeds(dataBloc.geteMin(), dataBloc.geteMax()));
        setIntervalleAnnee(new DateSeeds(dataBloc.getaMin(), dataBloc.getaMax()));
        setMargeSeeds(new MargeSeeds(dataBloc.getMarge()));

    }
    public double genererLongueur(){
        return getIntervalleLongueur().generate();
    }
    public double genererLargeur(){
        return getIntervalleLargeur().generate();
    }
    public double genererEpaisseur(){
        return getIntervalleEpaisseur().generate();
    }
    public Date generateDateFabrication(){
        return getIntervalleAnnee().generateWorkDate();
    }
    public double genererPrixDeRevient(Connection conn) throws Exception {
        // ! Recupere la moyenne des prix de revients des blocs originels actuelles
        double avgPrixDeRevient = getBlocStat(conn).getPrixDeRevientMoyenne();
        double randMarge = getMargeSeeds().generatePourcentage();
        double taux = avgPrixDeRevient * randMarge;
        System.out.println("RAND MARGE : "+randMarge);
        System.out.println("TAUX : "+taux);
        int signe = randomSigne();
        return avgPrixDeRevient + signe * taux;
    }

    private int randomSigne() {
        return Math.random() < 0.5 ? -1 : 1;
    }

    public IntegerSeeds getIntervalleLongueur() {
        return intervalleLongueur;
    }

    public void setIntervalleLongueur(IntegerSeeds intervalleLongueur) {
        this.intervalleLongueur = intervalleLongueur;
    }

    public IntegerSeeds getIntervalleLargeur() {
        return intervalleLargeur;
    }

    public void setIntervalleLargeur(IntegerSeeds intervalleLargeur) {
        this.intervalleLargeur = intervalleLargeur;
    }

    public IntegerSeeds getIntervalleEpaisseur() {
        return intervalleEpaisseur;
    }

    public void setIntervalleEpaisseur(IntegerSeeds intervalleEpaisseur) {
        this.intervalleEpaisseur = intervalleEpaisseur;
    }

    public DateSeeds getIntervalleAnnee() {
        return intervalleAnnee;
    }

    public void setIntervalleAnnee(DateSeeds intervalleAnnee) {
        this.intervalleAnnee = intervalleAnnee;
    }

    public double getMarge() {
        return getDataBloc().getMarge();
    }

    public DataBloc getDataBloc() {
        return dataBloc;
    }

    public void setDataBloc(DataBloc dataBloc) {
        this.dataBloc = dataBloc;
    }
    public MargeSeeds getMargeSeeds() {
        return margeSeeds;
    }
    public void setMargeSeeds(MargeSeeds margeSeeds) {
        this.margeSeeds = margeSeeds;
    }

}
