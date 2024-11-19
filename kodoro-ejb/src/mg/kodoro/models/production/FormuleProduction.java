package mg.kodoro.models.production;

import java.sql.Connection;

import mg.kodoro.bean.MaClassMAPTable;

public class FormuleProduction extends MaClassMAPTable{
    protected String idFormuleProduction;
    protected String desce;
    protected double prixDeRevient;

    public FormuleProduction(){setNomTable("FormuleProduction");}
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
        preparePk("FPR", "GET_formule_prod_seq");
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
