package mg.kodoro;

import java.sql.Connection;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.stock.MvtStockDimension;
import utilitaire.UtilDB;

public class Debug {
    public static void main(String[] args) {
    
        Connection conn = new UtilDB().GetConn();
        try {
            MvtStockDimension mvt = new MvtStockDimension();
            mvt.setidTra
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
}
