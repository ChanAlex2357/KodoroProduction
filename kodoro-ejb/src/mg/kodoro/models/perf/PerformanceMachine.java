package mg.kodoro.models.perf;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.ViewClassMAPTable;
import mg.kodoro.models.production.Machine;
import mg.kodoro.models.production.Production;

public class PerformanceMachine extends ViewClassMAPTable{
    private String idMachine;
    private double qteProduit;
    private double perfTheorique;
    private double perfPratique;
    private Production[] productions;
    private Machine machine;

    public PerformanceMachine() {
        setNomTable("PerformanceMachine");
    }
    public double getEcart(){
        return Math.abs(this.getPerfPratique() - this.getPerfTheorique());
    }
    // Getters et Setters
    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public double getPerfTheorique() {
        return perfTheorique;
    }

    public void setPerfTheorique(double perfTheorique) {
        this.perfTheorique = perfTheorique;
    }

    public double getPerfPratique() {
        return perfPratique;
    }

    public void setPerfPratique(double perfPratique) {
        this.perfPratique = perfPratique;
    }

    public Production[] getProductions() {return this.productions;}
    public Production[] getProductions(Connection conn)throws Exception {
        if (this.productions != null && this.productions.length > 0) {
            return this.getProductions();
        }
        Production ref = new Production();
        ref.setIdMachine(this.getIdMachine());
        Production[] productions = (Production[]) CGenUtil.rechercher(ref,null,null,conn,"");
        if (productions.length>0) {
            setProductions(productions);
        }
        return this.getProductions();
    }

    public void setProductions(Production[] productions) {
        this.productions = productions;
    }

    /**
     * Récupère toutes les machines avec leurs performances.
     */
    public static PerformanceMachine[] getAllPerformanceMachines(Connection conn) throws Exception {
        return getAllPerformanceMachines(null, conn);
    }

    /**
     * Récupère toutes les machines avec leurs performances.
     */
    public static PerformanceMachine[] getAllPerformanceMachines(String yearStr,Connection conn) throws Exception {
        String where = "";
        if (yearStr != null || yearStr != "") {
            where += "WHERE EXTRACT(YEAR FROM prod.dateProduction) = '"+yearStr+"'";
        }
        String requete = "SELECT \r\n" + //
                        "        prod.idMachine,\r\n" + //
                        "        AVG(prod.prTheorique) AS perfTheorique,\r\n" + //
                        "        AVG(prod.prPratique) AS perfPratique,\r\n" + //
                        "        COUNT(*) AS qteProduit\r\n" + //
                        "    FROM \r\n" + //
                        "        Production prod\r\n" + where + //
                        "    GROUP BY \r\n" + //
                        "        prod.idMachine";
        System.out.println("REQ : "+requete);
        PerformanceMachine[] performanceMachines = (PerformanceMachine[]) CGenUtil.rechercher(new PerformanceMachine(),requete,conn);
        if (performanceMachines.length > 0 ) {
            return performanceMachines;
        }
        return null;
    }

    /**
     * Récupère toutes les machines avec leurs performances.
     */
    public static PerformanceMachine[] getAllPerformanceMachines(int yearInt,Connection conn) throws Exception {
        if (yearInt == 0) {
            return getAllPerformanceMachines(conn);
        }
        return getAllPerformanceMachines(""+yearInt, conn);
    }

    /**
     * Récupère une machine spécifique par ID avec ses performances.
     */
    public static PerformanceMachine getByMachine(String idMachine, Connection conn) throws Exception {
        PerformanceMachine ref = new PerformanceMachine();
        PerformanceMachine[] productions = (PerformanceMachine[]) CGenUtil.rechercher(ref,null,null,conn,"");
        if (productions.length>0) {
            return productions[0];
        }
        return null;
    }

    @Override
    public String toString() {
        return "PerformanceMachine{" +
               "idMachine='" + idMachine + '\'' +
               ", perfTheorique=" + perfTheorique +
               ", perfPratique=" + perfPratique +
               ", productions=" + productions +
               '}';
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    public double getQteProduit() {
        return qteProduit;
    }
    public void setQteProduit(double qteProduit) {
        this.qteProduit = qteProduit;
    }
}
