package mg.kodoro.models.stock;

import java.sql.Connection;
public class AdminDetailEtatStockDimension extends AdminEtatStockDimension{
    
    public AdminDetailEtatStockDimension(){
        super();
        getAffichageTableStock().enableOriginalView();
    }
    @Override
    public AdminDetailEtatStockDimension getEtatStock(Connection conn) throws Exception {
        AdminDetailEtatStockDimension adminEtatStockDimension = new AdminDetailEtatStockDimension();
        DetailEtatStockDimension[] etatStockDimensions = new DetailEtatStockDimension().getEtatDeStocks(conn);
        adminEtatStockDimension.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDimension;
    }
    public AdminDetailEtatStockDimension getEtatStock( String idBloc, Connection conn) throws Exception {
        AdminDetailEtatStockDimension adminEtatStockDimension = new AdminDetailEtatStockDimension();
        DetailEtatStockDimension[] etatStockDimensions = new DetailEtatStockDimension().getEtatDeStocks(idBloc,conn);
        adminEtatStockDimension.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDimension;
    }
}
