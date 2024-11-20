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
        controllerDateProduction();
        controllerRelations(c);
        controllerFormule(c);
        controllerPrPratique(c);
        calculerPrixDeRevientTheorique(c);
    
        // Si le bloc n'a pas encore d'ID, le persister d'abord
        if (this.getBlocProduit(c).getIdBloc() == null) {
            this.getBlocProduit(c).createObject(c);
        }
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
    public void setBlocProduit(Bloc blocProduit) {
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
    // ------------------------------------------------------------
        
    
        private void controllerDateProduction() throws Exception {
            if (this.dateProduction == null) {
                this.dateProduction = this.getBlocProduit(null).getDateFabrication();
            }
        }
    
        private void controllerRelations(Connection conn) throws Exception {
            if (this.idBloc == null) {
                this.idBloc = this.getBlocProduit(conn).getIdBloc();
            }
            if (this.idMachine == null) {
                this.idMachine = this.getMachineProduction(conn).getIdMachine();
            }
        }
    
        private void controllerFormule(Connection conn) throws Exception {
            if (this.idFormuleProduction == null) {
                // Récupérer la première formule de production dans la base
                FormuleProduction formule = FormuleProduction.getById(null, conn);
                if (formule != null) {
                    this.idFormuleProduction = formule.getIdFormuleProduction();
                } else {
                    throw new Exception("Aucune formule de production trouvée.");
                }
            }
        }
    
        private void controllerPrPratique(Connection conn) throws Exception {
            if (this.prPratique == 0) {
                Bloc bloc = this.getBlocProduit(conn);
                this.prPratique = bloc.getPrixFabrication();
            }
        }
    
        private void calculerPrixDeRevientTheorique(Connection conn) throws Exception {
            FormuleProduction formule = this.getFormuleDeProduction(null);
            double pruFormule = formule.getPrixDeRevient();
            Bloc bloc = this.getBlocProduit(conn);
            this.prTheorique = bloc.getVolume() * pruFormule;
        }
    
        public void updatePrPratique(Connection conn) throws Exception {
            Bloc bloc = this.getBlocProduit(conn);
            this.prPratique = bloc.getPrixFabrication();
            this.updateToTable(conn); // Mettre à jour dans la base
        }
}
