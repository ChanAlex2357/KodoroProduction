package mg.kodoro.models.stock;

import java.sql.Connection;

public class AdminEtatStockDimension {
    public EtatStockDimension[] etatStockDimensions;

    public EtatStockDimension[] getEtatStockDimensions() {
        return etatStockDimensions;
    }

    public void setEtatStockDimensions(EtatStockDimension[] etatStockDimensions) {
        this.etatStockDimensions = etatStockDimensions;
    }

    public static AdminEtatStockDimension getEtatStock(Connection conn) throws Exception {
        AdminEtatStockDimension adminEtatStockDimension = new AdminEtatStockDimension();
        EtatStockDimension[] etatStockDimensions = EtatStockDimension.getEtatDeStocks(conn);
        adminEtatStockDimension.setEtatStockDimensions(etatStockDimensions);
        return adminEtatStockDimension;
    }
}
