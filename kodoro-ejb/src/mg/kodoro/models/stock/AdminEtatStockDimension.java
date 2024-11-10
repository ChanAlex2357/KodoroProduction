package mg.kodoro.models.stock;

import java.sql.Connection;

import mg.kodoro.models.stock.affichage.AffichageTableStock;

public class AdminEtatStockDimension {
    public EtatStockDimension[] etatStockDims;

    public EtatStockDimension[] getEtatStockDims() {
        return etatStockDims;
    }

    public void setEtatStockDims(EtatStockDimension[] etatStockDims) {
        this.etatStockDims = etatStockDims;
    }

    public static AdminEtatStockDimension getEtatStock(Connection conn) throws Exception {
        AdminEtatStockDimension adminEtatStockDim = new AdminEtatStockDimension();
        EtatStockDimension[] etatStockDims = new EtatStockDimension().getEtatDeStocks(conn);
        adminEtatStockDim.setEtatStockDims(etatStockDims);
        return adminEtatStockDim;
    }
    public String getHtml(){
        AffichageTableStock tableStock = new AffichageTableStock(this.getEtatStockDims());
        tableStock.disableOriginalView();
        return tableStock.getHtmlPattern();
    }
}
