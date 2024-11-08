package mg.kodoro.models.stock;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class EtatStockDimension extends MaClassMAPTable{;
    protected double quantite;
    protected String idOriginalSource;
    protected String idDimensionUsuels;
    protected double prixDeRevient;
    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObject'");
    }
    @Override
    public String getAttributIDName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributIDName'");
    }
    @Override
    public String getTuppleID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTuppleID'");
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
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
    public double getPrixDeRevient() {
        return prixDeRevient;
    }
    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public static EtatStockDimension[] getEtatDeStocks(Connection conn) throws Exception {
        EtatStockDimension[] etatStocks = (EtatStockDimension[]) CGenUtil.rechercher(new EtatStockDimension(),null,null,conn,"");
        if (etatStocks.length >  0) {
            return etatStocks;
        }
        return null;
    }
}
