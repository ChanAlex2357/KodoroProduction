package mg.kodoro.models.production;

import java.sql.Connection;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.annexe.Ressource;

public class FromuleProductionFille extends MaClassMAPTable {
    protected String idFormuleProductionFille;
    protected String idFormuleProduction;
    protected String idRessource;
    protected double quantite;
    
    protected Ressource ressource;
    
    public Ressource getRessource(Connection conn) throws Exception {
        if (this.ressource !=  null) {
            return this.ressource;
        }
        Ressource res = Ressource.getById(this.getIdRessource(), conn);
        setRessource(res);
        return this.ressource;
    }

    public FromuleProductionFille(){setNomTable("FromuleProductionFille");}

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("FromuleProductionFille");
        return super.createObject(c);
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("DFO", "GET_formule_prod_fille_seq");
        setIdFormuleProductionFille(makePK(c));
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
    public String getIdRessource() {
        return idRessource;
    }
    public void setIdRessource(String idRessource) {
        this.idRessource = idRessource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }
    public double  getMontantPrixRevient(Connection conn) throws Exception{
        double rpa = this.getRessource(conn).getPuAchat();
        double pr = rpa * this.getQuantite();
        return pr;
    }
}
