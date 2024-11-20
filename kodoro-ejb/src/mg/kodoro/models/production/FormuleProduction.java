package mg.kodoro.models.production;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class FormuleProduction extends MaClassMAPTable{
    protected String idFormuleProduction;
    protected String desce;
    protected double prixDeRevient;
    protected FromuleProductionFille[] detailsFormule;

    
    public FormuleProduction(){setNomTable("FormuleProduction");}
    
    public FromuleProductionFille[] getDetailsFormule(Connection conn) throws Exception {
        if (detailsFormule != null && detailsFormule.length > 0) {
            return this.detailsFormule;
        }
        FromuleProductionFille ref = new FromuleProductionFille();
        ref.setIdFormuleProduction(this.getIdFormuleProduction());
        FromuleProductionFille [] details = (FromuleProductionFille []) CGenUtil.rechercher(ref,null,null,conn,"");
        setDetailsFormule(details);
        return this.detailsFormule;
    }
    public void setDetailsFormule(FromuleProductionFille[] detailsFormule) {
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
        // Calculer le prix de revient du formule au moment de la creation du formule
        double pr = 0;
        for (FromuleProductionFille fromuleProductionFille : this.getDetailsFormule(c)) {
            fromuleProductionFille.setIdFormuleProduction(this.getIdFormuleProduction());
            pr += fromuleProductionFille.getMontantPrixRevient(c); // Sommer le prix de revient du detail
            fromuleProductionFille.createObject(c); // Save le detail de formule
        }
        this.setPrixDeRevient(pr);
        return super.createObject(c);
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
