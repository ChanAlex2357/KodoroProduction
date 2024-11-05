package mg.kodoro.bloc;

import java.util.Date;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import utilitaire.UtilDB;
import utils.TimeUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Bloc extends MaClassMAPTable{
    private String idBloc;
    
    private double longueur;
    private double largeur;
    private double epaisseur;
    private Date dateFabrication;
    private double prixFabrication;
    private String originalSource;
    private String parentSource;
    
    // Getters and Setters
    public String getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = validatePositiveDouble(longueur);
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = validatePositiveDouble(largeur);
        if (this.longueur <= this.largeur) {
            throw new IllegalArgumentException("La longueur doit être supérieure à la largeur.");
        }
    }

    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(String epaisseur) {
        this.epaisseur = validatePositiveDouble(epaisseur);
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) throws ParseException {
        if (dateFabrication == null) {
            throw new IllegalArgumentException ("Veuillez saisir une date valide");
        }
        this.dateFabrication = TimeUtils.convertToSqlDate(dateFabrication,"eng");
    }

    public double getPrixFabrication() {
        return prixFabrication;
    }

    public void setPrixFabrication(String prixFabrication) {
        this.prixFabrication = validatePositiveDouble(prixFabrication);
    }

    public String getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public String getParentSource() {
        return parentSource;
    }

    public void setParentSource(String parentSource) {
        this.parentSource = parentSource;
    }

    // Helper to validate positive double values
    private double validatePositiveDouble(String value) {
        double val = Double.parseDouble(value);
        if (val <= 0) {
            throw new IllegalArgumentException("La valeur doit être supérieure à zéro.");
        }
        return val;
    }

    // Calculate volume
    public double getVolume() {
        return longueur * largeur * epaisseur;
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        /// Generer le mouvement de stock dans la base distante
        return this;
    }

    @Override
    public String getAttributIDName() {
        return "idBloc";
    }

    @Override
    public String getTuppleID() {
        return this.getIdBloc();
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BLC", "GET_BLOC_SEQ");
        this.setIdBloc( makePK(c) );
    }

    static public Bloc[] getAllBlocs(){
        Bloc[] blocs = new Bloc[0];
        Connection conn = new UtilDB().GetConn();
        try {
            blocs = (Bloc[]) CGenUtil.rechercher(new Bloc() , null , null , conn , "");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blocs;
    } 
}
