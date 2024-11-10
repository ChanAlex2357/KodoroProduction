package mg.kodoro.models.stock;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class DetailEtatStockDimension extends EtatStockDimension {
    protected String idOriginalSource;
    
    public DetailEtatStockDimension(){setNomTable("EtatStockDimension");}

    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    @Override
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
        
    
    @Override
    public DetailEtatStockDimension[] getEtatDeStocks(Connection conn) throws Exception {
        DetailEtatStockDimension[] etatStocks = (DetailEtatStockDimension[]) CGenUtil.rechercher(new DetailEtatStockDimension(),null,null,conn," order by idDimensionUsuels");
        if (etatStocks.length >  0) {
            return etatStocks;
        }
        return null;
    }
    public DetailEtatStockDimension[] getEtatDeStocks(String idOriginalBloc,Connection conn) throws Exception {
        DetailEtatStockDimension ref = new DetailEtatStockDimension();
        ref.setIdOriginalSource(idOriginalBloc);
        DetailEtatStockDimension[] etatStocks = (DetailEtatStockDimension[]) CGenUtil.rechercher(ref,null,null,conn," order by idDimensionUsuels");
        if (etatStocks.length >  0) {
            return etatStocks;
        }
        return null;
    }
}
