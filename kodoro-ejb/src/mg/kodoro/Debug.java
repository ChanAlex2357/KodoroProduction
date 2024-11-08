package mg.kodoro;

import java.sql.Connection;

import mg.kodoro.models.Bloc;
import utilitaire.UtilDB;

public class Debug {
    public static void main(String[] args) {
        Bloc bloc = new Bloc();
        bloc.setIdBloc("BLC000002");

        Connection conn = new UtilDB().GetConn();
        try {
            bloc.getTransformations(conn);            
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
