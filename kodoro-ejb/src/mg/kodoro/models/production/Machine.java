package mg.kodoro.models.production;

import java.sql.Connection;
import java.sql.SQLException;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import utilitaire.UtilDB;

public class Machine extends MaClassMAPTable{
    protected String idMachine;
    protected String desce;

    public Machine(){setNomTable("Machine");}

    @Override
    public String getAttributIDName() {
        return "idMachine";
    }

    @Override
    public String getTuppleID() {
        return this.getIdMachine();
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("MAC", "GET_MACHINE_SEQ");
        setIdMachine(makePK(c));
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public static Machine[] getAllMachines(){
        Machine[] blocs = new Machine[0];
        Connection conn = new UtilDB().GetConn();
        try {
            blocs = (Machine[]) CGenUtil.rechercher(new Machine() , null , null , conn , "");
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
        return blocs;
    }

    public static Machine getById(String idMachine, Connection conn) throws Exception {
        Machine ref = new Machine();
        ref.setIdMachine(idMachine);
        Machine[] machines = (Machine[])CGenUtil.rechercher(ref,null,null,conn,"");
        if (machines.length < 0) {
            machines = (Machine[])CGenUtil.rechercher(new Machine(),null,null,conn,"");
        }
        return machines[0];
    }
}
