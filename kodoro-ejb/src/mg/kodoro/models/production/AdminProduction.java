package mg.kodoro.models.production;

import java.sql.Connection;
import java.text.ParseException;
import mg.kodoro.models.Bloc;

public class AdminProduction {
    protected String longueur , largeur , epaisseur , dateProduction , prixDeRevient , idMachine;
    protected Machine machine;
    protected Bloc bloc;
    public AdminProduction(String L , String l , String e , String daty , String pr ,String idMachine) throws ParseException{
        setLongueur(L);
        setLargeur(l);
        setEpaisseur(e);
        setDateProduction(daty);
        setPrixDeRevient(pr);
        setIdMachine(idMachine);
    }
    public Machine getMachine(Connection conn) throws Exception {
        if (this.machine == null) {
            setMachine( Machine.getById(this.getIdMachine(),conn));
        }
        return machine;
    }
    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    public Bloc getBloc() throws ParseException {
        if (this.bloc == null) {
            setBloc( new Bloc(getLongueur(), getLargeur(), getEpaisseur(), getDateProduction(), getPrixDeRevient()));
        }
        return this.bloc;
    }
    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public Production produire(Connection conn)throws Exception{
        return this.getMachine(conn).produire(this.getBloc(), conn);
    }
    public String getLongueur() {
        return longueur;
    }
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }
    public String getLargeur() {
        return largeur;
    }
    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }
    public String getEpaisseur() {
        return epaisseur;
    }
    public void setEpaisseur(String epaisseur) {
        this.epaisseur = epaisseur;
    }
    public String getDateProduction() {
        return dateProduction;
    }
    public void setDateProduction(String dateProduction) {
        this.dateProduction = dateProduction;
    }
    public String getPrixDeRevient() {
        return prixDeRevient;
    }
    public void setPrixDeRevient(String prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }
    public String getIdMachine() {
        return idMachine;
    }
    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }
    
}
