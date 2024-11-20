package mg.kodoro.models.production;

import java.sql.Connection;

import mg.kodoro.bean.MaClassMAPTable;

public class FromuleProductionFille extends MaClassMAPTable {
    protected String idFormuleProductionFille;
    protected String idFormuleProduction;
    protected double quantite;
    
    public FromuleProductionFille(){setNomTable("FromuleProductionFille");}

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("FromuleProductionFille");
        return super.createObject(c);
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("DFO", "GET_formule_prod_fille_seq");
    }
    @Override
    public String getAttributIDName() {
        return "idFormuleProductionFille";
    }

    @Override
    public String getTuppleID() {
        return this.getIdFormuleProductionFille();
    }

    public String getIdFormuleProductionFille() {
        return idFormuleProductionFille;
    }

    public void setIdFormuleProductionFille(String idFormuleProductionFille) {
        this.idFormuleProductionFille = idFormuleProductionFille;
    }

    public String getIdFormuleProduction() {
        return idFormuleProduction;
    }

    public void setIdFormuleProduction(String idFormuleProduction) {
        this.idFormuleProduction = idFormuleProduction;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    
}
