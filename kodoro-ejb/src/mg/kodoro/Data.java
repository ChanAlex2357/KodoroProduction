package mg.kodoro;

import java.sql.Connection;

import mg.kodoro.models.annexe.Ressource;
import mg.kodoro.models.annexe.TypeMvt;
import mg.kodoro.models.annexe.TypeProduit;
import utilitaire.UtilDB;

public class Data {

    public static void main(String[] args) {
        
        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            // -----------------------------------

                genereTypeMvvt(conn);

            // -------------------------------------- 
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


    static void genererRessource(Connection conn) throws Exception{
        Ressource[] ressources = Ressource.getAllRessources(conn);
        // for (Ressource ressource : ressources) {
            ressources[2].genererProduit(conn);
        // }

    }

    static void genereTypeMvvt(Connection conn)throws Exception {
        TypeMvt[] mvts= new TypeMvt[2];

        mvts[0] = new TypeMvt();
        mvts[0].setVal("Entree");
        mvts[0].setDesce("ENTREE");

        mvts[1] = new TypeMvt();
        mvts[1].setVal("Sortie");
        mvts[1].setDesce("SORTIE");

        for (TypeMvt typeMvt : mvts) {
            typeMvt.createObject(conn);
        }
    }
}