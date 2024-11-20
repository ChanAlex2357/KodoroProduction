package mg.kodoro.models.production;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import utilitaire.UtilDB;

public class FormuleProduction extends MaClassMAPTable{
    protected String idFormuleProduction;
    protected String desce;
    protected double prixDeRevient;
    protected FormuleProductionFille[] detailsFormule;
    public FormuleProduction(){setNomTable("FormuleProduction");}
    

    public FormuleProductionFille[] getDetailsFormule() {
        Connection conn = new UtilDB().GetConn();
        try {
            return getDetailsFormule(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {conn.close();}catch(Exception e) {}
        }
        return null;
    }
    public FormuleProductionFille[] getDetailsFormule(Connection conn) throws Exception {
        if (detailsFormule != null && detailsFormule.length > 0) {
            return this.detailsFormule;
        }
        FormuleProductionFille ref = new FormuleProductionFille();
        ref.setIdFormuleProduction(this.getIdFormuleProduction());
        FormuleProductionFille [] details = (FormuleProductionFille []) CGenUtil.rechercher(ref,null,null,conn,"");
        setDetailsFormule(details);
        return this.detailsFormule;
    }
    public void setDetailsFormule(FormuleProductionFille[] detailsFormule) {
        this.detailsFormule = detailsFormule;
    }

    public FormuleProduction getById(Connection conn) throws Exception {
        String id = this.getIdFormuleProduction();
        return FormuleProduction.getById(id, conn);
    }
    public static FormuleProduction getById( String id ,Connection conn) throws Exception {
        FormuleProduction ref = new FormuleProduction();
        ref.setIdFormuleProduction(id);
        FormuleProduction[] formules = (FormuleProduction[]) CGenUtil.rechercher(ref,null,null,conn,"");
        if (formules.length < 0) {
            formules = (FormuleProduction[]) CGenUtil.rechercher(new FormuleProduction(),null,null,conn,"");  
        }
        return formules[0];
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("FormuleProduction");
        construirePK(c);
        super.createObject(c);
        // Calculer le prix de revient du formule au moment de la creation du formule
        double pr = 0;
        for (FormuleProductionFille formuleFille : this.getDetailsFormule(c)) {
            formuleFille.setIdFormuleProduction(this.getIdFormuleProduction());
            pr += formuleFille.getMontantPrixRevient(c); // Sommer le prix de revient du detail
            formuleFille.createObject(c); // Save le detail de formule
        }
        this.setPrixDeRevient(pr);
        updateToTable(c);
        return this;
    }
    @Override
    public String getAttributIDName() {
        return "idFormuleProduction";
    }

    @Override
    public String getTuppleID() {
        return this.getIdFormuleProduction();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("FOR", "GET_formule_prod_seq");
        setIdFormuleProduction(makePK(c));
    }

    public String getIdFormuleProduction() {
        return idFormuleProduction;
    }
    public void setIdFormuleProduction(String idFormuleProduction) {
        this.idFormuleProduction = idFormuleProduction;
    }
    public String getDesce() {
        return desce;
    }
    public void setDesce(String desce) {
        this.desce = desce;
    }
    public double getPrixDeRevient() {
        return prixDeRevient;
    }
    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }    
}
