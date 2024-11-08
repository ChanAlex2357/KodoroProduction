package mg.kodoro.models.stock;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class MvtStockDimension extends MaClassMAPTable{
    protected String idMvtStockDimension;
    protected String idTransformationFille;
    protected double entree;
    protected double sortie;
    protected String idOriginalSource;
    protected String idDimensionUsuels;
    protected double prixDeRevient;

    public MvtStockDimension(){
        
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

    public static MvtStockDimension getStockDimension(String idDimension , String idOrigine , Connection conn) throws Exception {
        MvtStockDimension stock = new MvtStockDimension();
        stock.setIdDimensionUsuels(idDimension);
        stock.setIdOriginalSource(idOrigine);

        MvtStockDimension[] stockDimensions = (MvtStockDimension[]) CGenUtil.rechercher(stock,null,null,conn,"");

        if (stockDimensions.length > 0) {
            return stockDimensions[0];
        }
        return null;
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
}