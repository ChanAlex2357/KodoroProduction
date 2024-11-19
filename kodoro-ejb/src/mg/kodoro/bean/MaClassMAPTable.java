package mg.kodoro.bean;

import java.sql.Connection;
import java.sql.SQLException;

import bean.ClassMAPTable;
import utilitaire.UtilDB;

public abstract class MaClassMAPTable extends ClassMAPTable{
    public MaClassMAPTable createObject(Connection c) throws Exception {
        if (this.getTuppleID() == null || this.getTuppleID().compareToIgnoreCase("") == 0 || this.getTuppleID().compareToIgnoreCase("0") == 0) {
            this.construirePK(c);
        }
        
        int result = this.insertToTable(c);
        if (result == 0) {
            return null;
        }
        return this;
    }
    
    public MaClassMAPTable createObject() throws SQLException{
        Connection conn = new UtilDB().GetConn();
        MaClassMAPTable result = null;
        try {
            conn.setAutoCommit(false);
            result = this.createObject(conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
        finally {
            conn.close();
        }
        return result;
    }

    public static void createObjects(MaClassMAPTable[] objects ) throws SQLException{
        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            MaClassMAPTable.createObjects(objects, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
        finally {
            conn.close();
        }
    }
    public static void createObjects(MaClassMAPTable[] objects , Connection conn) throws Exception{
        System.out.println(objects);
        for (MaClassMAPTable maClassMAPTable : objects) {
            maClassMAPTable.createObject(conn);
        }
    }
}
