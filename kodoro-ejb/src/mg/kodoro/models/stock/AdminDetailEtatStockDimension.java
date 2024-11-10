package mg.kodoro.models.stock;

import java.sql.Connection;

import mg.kodoro.models.stock.affichage.AffichageTableStock;

public class AdminDetailEtatStockDimension{
    public DetailEtatStockDimension[] etatStockDimensions;

    public DetailEtatStockDimension[] getEtatStockDimensions() {
        return etatStockDimensions;
    }

    public void setEtatStockDimensions(DetailEtatStockDimension[] etatStockDimensions) {
        this.etatStockDimensions = etatStockDimensions;
    }

    public static AdminDetailEtatStockDimension getEtatStock(Connection conn) throws Exception {
        AdminDetailEtatStockDimension adminEtatStockDimension = new AdminDetailEtatStockDimension();
        DetailEtatStockDimension[] etatStockDimensions = new DetailEtatStockDimension().getEtatDeStocks(conn);
        adminEtatStockDimension.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDimension;
    }
    public static AdminDetailEtatStockDimension getEtatStock( String idBloc, Connection conn) throws Exception {
        AdminDetailEtatStockDimension adminEtatStockDimension = new AdminDetailEtatStockDimension();
        DetailEtatStockDimension[] etatStockDimensions = new DetailEtatStockDimension().getEtatDeStocks(idBloc,conn);
        adminEtatStockDimension.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDimension;
    }
    public String getHtml() {
        AffichageTableStock tableStock = new AffichageTableStock(this.getEtatStockDimensions());
        tableStock.enableOriginalView();
        return tableStock.getHtmlPattern();
    }

}
