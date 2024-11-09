package mg.kodoro.models.stock;

import java.sql.Connection;
import java.sql.Date;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class MvtStockDimension extends MaClassMAPTable {
    
    protected String idMvtStockDimension;
    protected String idTransformationFille;
    protected double entree;
    protected double sortie;
    protected String idOriginalSource;
    protected String idDimensionUsuels;
    protected double prixDeRevient;
    protected double prixDeRevientUnitaire;
    protected double prixDeVente;
    protected double prixDeVenteUnitaire;
    protected Date   daty;

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public MvtStockDimension() {
        setNomTable("MvtStockDimension");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        System.out.println( "ID MVT -- MSD");
        this.preparePk("MSD", "GET_MSD_SEQ");
        this.setIdMvtStockDimension( this.makePK(c) );
    }

    public String getIdMvtStockDimension() {
        return idMvtStockDimension;
    }

    public void setIdMvtStockDimension(String idMvtStockDimension) {
        this.idMvtStockDimension = idMvtStockDimension;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public String getIdOriginalSource() {
        return idOriginalSource;
    }

    public void setIdOriginalSource(String idOriginalSource) {
        this.idOriginalSource = idOriginalSource;
    }

    public String getIdDimensionUsuels() {
        return idDimensionUsuels;
    }

    public void setIdDimensionUsuels(String idDimensionUsuels) {
        this.idDimensionUsuels = idDimensionUsuels;
    }

    public static MvtStockDimension getStockDimension(String idDimension, String idOrigine, Connection conn) throws Exception {
        MvtStockDimension stock = new MvtStockDimension();
        stock.setIdDimensionUsuels(idDimension);
        stock.setIdOriginalSource(idOrigine);

        MvtStockDimension[] stockDimensions = (MvtStockDimension[]) CGenUtil.rechercher(stock, null, null, conn, "");

        if (stockDimensions.length > 0) {
            return stockDimensions[0];
        }
        return null;
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("MvtStockDimension");
        // Contolle de donnee
        controllerPrixMvt();
        
        if (this.getTuppleID() == null || this.getTuppleID().compareToIgnoreCase("") == 0 || this.getTuppleID().compareToIgnoreCase("0") == 0) {
            this.construirePK(c);
        }
        
        System.out.println("-- GENERER MVT STOCK ---");
        System.out.println(this);
        return super.createObject(c);
    }
    
    protected void controllerPrixMvt(){
        if (this.getEntree() > 0) {
            this.controllerPrixRevient();
        }
        else if (this.getSortie() > 0) {
            this.controllerPrixVente();
        }
    }

    protected void controllerPrixRevient() {
        if (this.prixDeRevient == 0 && this.prixDeRevientUnitaire > 0) {
            setPrixDeRevient( this.getPrixDeRevientUnitaire() * this.getEntree());
        }
        else if (getPrixDeRevientUnitaire() == 0 && getPrixDeRevient() >0) {
            setPrixDeRevientUnitaire(getPrixDeRevient() / this.getEntree());
        }
    }

    protected void controllerPrixVente() {
        if (this.prixDeVente == 0 && this.prixDeVenteUnitaire > 0) {
            setPrixDeVente( this.getPrixDeVenteUnitaire() * this.getSortie());
        }
        else if (getPrixDeVenteUnitaire() == 0 && getPrixDeVente() >0) {
            setPrixDeVenteUnitaire(getPrixDeVente() / this.getSortie());
        }


    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        return this;
    }

    @Override
    public String getAttributIDName() {
        return "idMvtStockDimension";
    }

    @Override
    public String getTuppleID() {
        return this.getIdMvtStockDimension();
    }

    public String getIdTransformationFille() {
        return idTransformationFille;
    }

    public void setIdTransformationFille(String idTransformationFille) {
        this.idTransformationFille = idTransformationFille;
    }

    public double getPrixDeRevient() {
        return prixDeRevient;
    }

    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    @Override
    public String toString() {
        return "MvtStockDimension {" +
                "idMvtStockDimension='" + idMvtStockDimension + '\'' +
                ", idTransformationFille='" + idTransformationFille + '\'' +
                ", entree=" + entree +
                ", sortie=" + sortie +
                ", idOriginalSource='" + idOriginalSource + '\'' +
                ", idDimensionUsuels='" + idDimensionUsuels + '\'' +
                ", prixDeRevient=" + prixDeRevient +
                '}';
    }

    public double getPrixDeRevientUnitaire() {
        return prixDeRevientUnitaire;
    }

    public void setPrixDeRevientUnitaire(double prixDeRevientUnitaire) {
        this.prixDeRevientUnitaire = prixDeRevientUnitaire;
    }

    public double getPrixDeVente() {
        return prixDeVente;
    }

    public void setPrixDeVente(double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }

    public double getPrixDeVenteUnitaire() {
        return prixDeVenteUnitaire;
    }

    public void setPrixDeVenteUnitaire(double prixDeVenteUnitaire) {
        this.prixDeVenteUnitaire = prixDeVenteUnitaire;
    }

    public void updatePrixDeRevient(double taux,Connection conn) throws Exception {
        this.setPrixDeRevient( this.getPrixDeRevient() * taux);
        this.setPrixDeRevientUnitaire( this.getPrixDeRevientUnitaire() * taux);

        this.updateToTable(conn);
    }
}
