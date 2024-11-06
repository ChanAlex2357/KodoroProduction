package mg.kodoro.models;

import java.sql.Connection;
import java.sql.SQLException;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;
import utilitaire.UtilDB;

public class DimensionUsuels extends MaClassMAPTable {
    private String idDimensionUsuels;
    private double longueur;
    private double largeur;
    private double epaisseur;
    private double prixVente;
    private String desce;

    // Constructeur par défaut
    public DimensionUsuels() {
        setNomTable("DIMENSIONUSUELS");
    }

    // Constructeur avec paramètres double
    public DimensionUsuels(double L, double l, double e, double prixVente, String desce) {
        setLongueur(L);
        setLargeur(l);
        setEpaisseur(e);
        setPrixVente(prixVente);
        setDesce(desce);
    }

    // Constructeur avec paramètres String
    public DimensionUsuels(String longueur, String largeur, String epaisseur, String prixVente, String desce) {
        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epaisseur);
        setPrixVente(prixVente);
        setDesce(desce);
    }

    // Getters et Setters
    public String getIdDimensionUsuels() {
        return idDimensionUsuels;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public void setIdDimensionUsuels(String idDimensionUsuels) {
        this.idDimensionUsuels = idDimensionUsuels;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = ValidationUtils.validatePositiveStringDouble(longueur);
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = ValidationUtils.validatePositiveStringDouble(largeur);
    }

    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

    public void setEpaisseur(String epaisseur) {
        this.epaisseur = ValidationUtils.validatePositiveStringDouble(epaisseur);
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public void setPrixVente(String prixVente) {
        this.prixVente = ValidationUtils.validatePositiveStringDouble(prixVente);
    }

    // Calcul du volume
    public double getVolume() {
        return getLongueur() * getLargeur() * getEpaisseur();
    }

    // Calcul du rapport volume/prix
    public double getRapportVolumePrix() {
        return prixVente != 0 ? getVolume() / prixVente : 0;
    }

    @Override
    public String toString() {
        return "DimensionUsuels{" +
                "idDimensionUsuels='" + idDimensionUsuels + '\'' +
                ", L=" + this.getLongueur() +
                ", l=" + this.getLargeur() +
                ", e=" + this.getEpaisseur() +
                ", prixVente=" + this.getPrixVente() +
                ", desce='" + desce + '\'' +
                '}';
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        return this;
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        System.out.println("-/-/-/-/-//-/-/-");
        setNomTable("DIMENSIONUSUELS");
        if (this.getTuppleID() == null || this.getTuppleID().isEmpty() || this.getTuppleID().equals("0")) {
            this.construirePK(c);
        }
        System.out.println("--- DETAILS ---");
        System.out.println(this.toString());
        return super.createObject(c);
    }

    @Override
    public String getAttributIDName() {
        return "idDimensionUsuels";
    }

    @Override
    public String getTuppleID() {
        return getIdDimensionUsuels();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("DIMUS", "GET_DIMUS_SEQ");
        this.setIdDimensionUsuels(makePK(c));
    }

    public static DimensionUsuels[] getAllDimensionsUsuelles(){
        DimensionUsuels[] dimensions = new DimensionUsuels[0];
        Connection conn = new UtilDB().GetConn();
        try {
            dimensions = (DimensionUsuels[]) CGenUtil.rechercher(new DimensionUsuels() , null , null , conn , "");
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
        return dimensions;
    }
}
