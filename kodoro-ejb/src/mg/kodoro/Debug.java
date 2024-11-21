package mg.kodoro;

import java.sql.Connection;
import java.sql.SQLException;

import mg.kodoro.models.perf.PerformanceMachine;
import utilitaire.UtilDB;

public class Debug {
    public static void main(String[] args) {
        Connection conn = new UtilDB().GetConn();
        try {
            PerformanceMachine[] ttrf = PerformanceMachine.getAllPerformanceMachines(conn);
            System.err.println(ttrf[0].getIdMachine()+" | "+ttrf[0].getPerfPratique()+" | "+ttrf[0].getPerfTheorique());
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
