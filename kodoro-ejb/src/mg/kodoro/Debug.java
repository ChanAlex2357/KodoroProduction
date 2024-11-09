package mg.kodoro;

import java.sql.Connection;
import java.sql.SQLException;
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.models.transformation.Transformation;
import mg.kodoro.models.transformation.TransformationFille;
import mg.kodoro.models.transformation.TransformationFilleLib;
import utilitaire.UtilDB;

public class Debug {
    public static void main(String[] args) {
        Connection conn = new UtilDB().GetConn();
        try {
            TransformationFilleLib ttrf = TransformationFille.getById("TRANSF000045", conn);
            System.err.println(ttrf);
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
