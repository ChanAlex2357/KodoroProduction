package mg.kodoro.models.stock;

import java.sql.Connection;

import mg.kodoro.models.stock.affichage.AffichageTableStock;

public class AdminEtatStockDimension {
    public EtatStockDimension[] etatStockDimensions;
    public AffichageTableStock affichageTableStock;

    public AdminEtatStockDimension(){
        setAffichageTableStock(new AffichageTableStock( this ));
        getAffichageTableStock().disableOriginalView();
    }
    public AffichageTableStock getAffichageTableStock() {
        return affichageTableStock;
    }

    public void setAffichageTableStock(AffichageTableStock affichageTableStock) {
        this.affichageTableStock = affichageTableStock;
    }


    public EtatStockDimension[] getEtatStockDimensions() {
        return etatStockDimensions;
    }

    public void setEtatStockDimensions(EtatStockDimension[] etatStockDimensions) {
        this.etatStockDimensions = etatStockDimensions;
    }

    public AdminEtatStockDimension getEtatStock(Connection conn) throws Exception {
        AdminEtatStockDimension adminEtatStockDim = new AdminEtatStockDimension();
        EtatStockDimension[] etatStockDimensions = new EtatStockDimension().getEtatDeStocks(conn);
        adminEtatStockDim.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDim;
    }
    public String getHtml(){
        return getAffichageTableStock().getHtmlPattern();
    }
}
