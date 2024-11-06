package mg.kodoro;

import java.sql.Connection;
import java.sql.SQLException;
import mg.kodoro.models.DimensionUsuels;
import utilitaire.UtilDB;

public class Main {
    public static void main(String[] args) {
        DimensionUsuels[] dimensionUsuels = new DimensionUsuels[4];
        
        dimensionUsuels[0] = new DimensionUsuels(2.5,1.5,0.2,2500,"King size");
        dimensionUsuels[1] = new DimensionUsuels(1.7,1.3,0.15,2000,"Medium size");
        dimensionUsuels[2] = new DimensionUsuels(1.5,1.3,0.15,	1500,"Lite size");
        dimensionUsuels[3] = new DimensionUsuels(0.1,0.1,0.1,1000,"Mini size");

        Connection conn = new UtilDB().GetConn();
        try {
        for (DimensionUsuels dim : dimensionUsuels) {
            System.out.println(" INSERTION ");
                conn.setAutoCommit(false);
                System.out.println(dim);
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
