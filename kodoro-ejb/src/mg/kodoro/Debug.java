package mg.kodoro;

import java.sql.Connection;
import java.sql.SQLException;

import mg.kodoro.models.annexe.Produit;
import utilitaire.UtilDB;

public class Debug {
    public static void main(String[] args) {
        Connection conn = new UtilDB().GetConn();
        try {
            
            Produit.getAllProduits(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
