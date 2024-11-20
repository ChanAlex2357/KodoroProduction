package mg.kodoro.models.production;

import java.sql.Connection;
import java.sql.Date;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.Bloc;

public class Production extends MaClassMAPTable{
    protected String idProduction;
    protected String idBloc;
    protected String idMachine;
    protected String idFormuleProduction;
    protected Date   dateProduction;
    protected double prTheorique;
    protected double prPratique;
    // 
    protected Bloc blocProduit;
    protected Machine machineProduction;
    protected FormuleProduction formuleDeProduction;
    public Production(){
        setNomTable("Production");
    }

    @Override
    public Production createObject(Connection c) throws Exception {
        setNomTable("Production");
        // Controler les donnees
        return (Production) super.createObject(c);
    }
    public String getIdProduction() {
        return idProduction;
    }
    public void setIdProduction(String idProduction) {
        this.idProduction = idProduction;
    }
    public String getIdBloc() {
        return idBloc;
    }
    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
    }
    public String getIdMachine() {
        return idMachine;
    }
    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }
    public String getIdFormuleProduction() {
        return idFormuleProduction;
    }
    public void setIdFormuleProduction(String idFormuleProduction) {
        this.idFormuleProduction = idFormuleProduction;
    }
    public Date getDateProduction() {
        return dateProduction;
    }
    public void setDateProduction(Date dateProduction) {
        this.dateProduction = dateProduction;
    }
    public double getPrTheorique() {
        return prTheorique;
    }
    public void setPrTheorique(double prTheorique) {
        this.prTheorique = prTheorique;
    }
    public double getPrPratique() {
        return prPratique;
    }
    public void setPrPratique(double prPratique) {
        this.prPratique = prPratique;
    }
    public Bloc getBlocProduit(Connection conn) throws Exception {
        if (this.blocProduit!= null) {
            return this.blocProduit;
        }
        Bloc b = Bloc.getById(this.getIdBloc(), conn);
        setBlocProduit(b);
        return this.blocProduit;
    }
    protected void setBlocProduit(Bloc blocProduit) {
        if (blocProduit.getIdBloc() != this.getIdBloc()) {
            setIdBloc(blocProduit.getIdBloc());
        }
        setDateProduction(blocProduit.getDateFabrication());
        this.blocProduit = blocProduit;
    }
    public Machine getMachineProduction(Connection conn) throws Exception {
        if (this.machineProduction != null) {
            return this.machineProduction;
        }

        Machine machine = Machine.getById(this.getIdMachine(),conn);
        setMachineProduction(machine);
        return this.machineProduction;
    }
    protected void setMachineProduction(Machine machineProduction) {
        if (machineProduction.getIdMachine() != this.getIdMachine()) {
            setIdMachine( machineProduction.getIdMachine());
        }
        this.machineProduction = machineProduction;
    }
    public FormuleProduction getFormuleDeProduction(Connection conn) throws Exception {
        if (this.formuleDeProduction != null) {
            return this.formuleDeProduction;
        }
        FormuleProduction formule = FormuleProduction.getById(this.getIdFormuleProduction(), conn);
        setFormuleDeProduction(formule);
        return this.formuleDeProduction;
    }
    protected void setFormuleDeProduction(FormuleProduction formuleDeProduction) {
        if (formuleDeProduction.getIdFormuleProduction() != this.getIdFormuleProduction()) {
            setIdFormuleProduction(formuleDeProduction.getIdFormuleProduction());
        }
        this.formuleDeProduction = formuleDeProduction;
    }
    @Override
    public String getAttributIDName() {
        return "idProduction";
    }
    @Override
    public String getTuppleID() {
        return this.getIdProduction();
    }
    
}
