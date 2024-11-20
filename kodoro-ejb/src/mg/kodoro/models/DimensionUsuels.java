package mg.kodoro.models;

import java.sql.Connection;
import java.sql.SQLException;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.dimension.ClassDimension;
import mg.kodoro.utils.ValidationUtils;
import utilitaire.UtilDB;

public class DimensionUsuels extends ClassDimension {
    private String idDimensionUsuels;
    private double prixVente;
    private double rapportVolumePrix;
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

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public void setPrixVente(String prixVente) {
        this.prixVente = ValidationUtils.validatePositiveStringDouble(prixVente);
    }

    // Calcul du rapport volume/prix
    public double getRapportVolumePrix() {
        if (this.rapportVolumePrix == 0) {
            setRapportVolumePrix(getVolume() != 0 ? prixVente / getVolume() : 0);
        }
        return this.rapportVolumePrix;
    }

    @Override
    public String toString() {
        return "DimensionUsuels{" +
                "idDimensionUsuels='" + idDimensionUsuels + '\'' +
                ", L=" + this.getLongueur() +
                ", l=" + this.getLargeur() +
                ", e=" + this.getEpaisseur() +
                ", volume=" + this.getVolume() +
                ", v/p =" + this.getRapportVolumePrix() +
                ", prixVente=" + this.getPrixVente() +
                ", desce='" + desce + '\'' +
                '}';
    }


    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        System.out.println("-/-/-/-/-//-/-/-");
        setNomTable("DIMENSIONUSUELS");
        controlerVolume();
        controlerRapportVolumePrix();
        if (this.getTuppleID() == null || this.getTuppleID().isEmpty() || this.getTuppleID().equals("0")) {
            this.construirePK(c);
        }
        System.out.println("--- DETAILS ---");
        System.out.println(this.toString());
        return super.createObject(c);
    }
    private void controlerVolume() {
        if (this.volume <= 0 ) {
            this.getVolume();
        }
    }
    private void controlerRapportVolumePrix() {
        if (this.rapportVolumePrix <= 0 ) {
            this.getRapportVolumePrix();
        }
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public void setRapportVolumePrix(double rapportVolumePrix) {
        this.rapportVolumePrix = rapportVolumePrix;
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

    public double getMontantVente(double qte){
        return this.getPrixVente() * qte;
    }

    public double getEstimationQte(Bloc bloc) {
        double qte = 0;
        qte =  bloc.getVolume() / this.getVolume();
        return qte;
    }

    public static DimensionUsuels getDimensionUsuelsWithMinimalVolume(Connection conn) throws Exception{
        DimensionUsuels[] dimensionUsuels = (DimensionUsuels[]) CGenUtil.rechercher(new DimensionUsuels() , null , null , conn , " order by volume asc" );
        if (dimensionUsuels.length > 0 ) {
            return dimensionUsuels[0];
        }
        return null; 
    }

    public static DimensionUsuels getDimensinoUsuelsWithMaxRapportVolumePrix(Connection conn)throws Exception{
        DimensionUsuels[] dimensionUsuels = (DimensionUsuels[]) CGenUtil.rechercher(new DimensionUsuels() , null , null , conn , " order by rapportvolumeprix desc" );
        if (dimensionUsuels.length > 0 ) {
            return dimensionUsuels[0];
        }
        return null;
    }
}
