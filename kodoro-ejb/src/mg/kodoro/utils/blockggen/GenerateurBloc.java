package mg.kodoro.utils.blockggen;

import java.sql.Connection;
import java.sql.Date;


import mg.kodoro.helpers.seeds.DateSeeds;
import mg.kodoro.helpers.seeds.IntegerSeeds;
import mg.kodoro.models.Bloc;
import mg.kodoro.models.blockggen.DataBloc;

public class GenerateurBloc {
    private DataBloc dataBloc;
    

    private IntegerSeeds intervalleLongueur;
    private IntegerSeeds intervalleLargeur;
    private IntegerSeeds intervalleEpaisseur;
    private DateSeeds intervalleAnnee;
    private BlocStat blocStat;
    public GenerateurBloc(DataBloc dataBloc) {
        setDataBloc(dataBloc);
        this.intervalleLongueur = new IntegerSeeds(dataBloc.getLMin(), dataBloc.getLMax());
        this.intervalleLargeur = new IntegerSeeds(dataBloc.getlMin(), dataBloc.getlMax());
        this.intervalleEpaisseur = new IntegerSeeds(dataBloc.geteMin(), dataBloc.geteMax());
        this.intervalleAnnee = new DateSeeds(dataBloc.getaMin(), dataBloc.getaMax());
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
        double marge = getMarge();
        // ! Recupere la moyenne des prix de revients des blocs originels actuelles
        double avgPrixDeRevient = Bloc.getBlocStat(conn).getPrixDeRevientMoyenne();
        double taux = avgPrixDeRevient * marge;
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
    public BlocStat getBlocStat() {
        return blocStat;
    }

    public void setBlocStat(BlocStat blocStat) {
        this.blocStat = blocStat;
    }
}
