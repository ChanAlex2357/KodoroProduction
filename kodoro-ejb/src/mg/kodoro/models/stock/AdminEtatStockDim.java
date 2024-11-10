package mg.kodoro.models.stock;

import java.sql.Connection;

public class AdminEtatStockDim {
    public EtatStockDim[] etatStockDims;

    public EtatStockDim[] getEtatStockDims() {
        return etatStockDims;
    }

    public void setEtatStockDims(EtatStockDim[] etatStockDims) {
        this.etatStockDims = etatStockDims;
    }

    public static AdminEtatStockDim getEtatStock(Connection conn) throws Exception {
        AdminEtatStockDim adminEtatStockDim = new AdminEtatStockDim();
        EtatStockDim[] etatStockDims = EtatStockDim.getEtatDeStocks(conn);
        adminEtatStockDim.setEtatStockDims(etatStockDims);
        return adminEtatStockDim;
    }
}
