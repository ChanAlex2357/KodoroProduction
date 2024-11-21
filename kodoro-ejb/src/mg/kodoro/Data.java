package mg.kodoro;

import java.sql.Connection;

import mg.kodoro.models.annexe.TypeProduit;
import utilitaire.UtilDB;

public class Data {

    public static void main(String[] args) {
        
        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            genererTypeProduitRessource(conn);
            conn.commit();
        } catch (Exception e) {
            try { conn.rollback(); } catch (Exception err) { err.printStackTrace();}
            e.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (Exception e) {e.printStackTrace();}
        }
    }

    static void genererTypeProduitRessource(Connection conn) throws Exception {
        TypeProduit tpr = new TypeProduit();
        tpr.setVal("Ressource");
        tpr.setDesce("RESSOURCE");
        tpr.createObject(conn);
    }
}