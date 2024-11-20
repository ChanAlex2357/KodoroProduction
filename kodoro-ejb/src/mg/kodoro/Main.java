package mg.kodoro;

import java.sql.Connection;
import java.sql.SQLException;
import mg.kodoro.models.DimensionUsuels;
import utilitaire.UtilDB;

public class Main {
    public static void main(String[] args) {
        DimensionUsuels[] dimensionUsuels = new DimensionUsuels[4];
        
        dimensionUsuels[0] = new DimensionUsuels(16,4,2,20000,"King size");
        dimensionUsuels[1] = new DimensionUsuels(10,7,1,12000,"Medium size");
        dimensionUsuels[2] = new DimensionUsuels(5,1,1,	600,"Lite size");

        Connection conn = new UtilDB().GetConn();
        try {
        for (DimensionUsuels dim : dimensionUsuels) {
                conn.setAutoCommit(false);
                dim.createObject(conn);
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
