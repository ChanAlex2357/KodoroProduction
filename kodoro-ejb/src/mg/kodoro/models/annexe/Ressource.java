package mg.kodoro.models.annexe;

import java.sql.Connection;

import mg.kodoro.bean.MaClassMAPTable;

public class Ressource extends MaClassMAPTable{
    protected String idRessource;
    protected String desce;
    protected String idUnite;
    protected double puAchat;
    protected Unite unite;

    
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("RES", "GET_RESSOURCE_SEQ");
        setIdRessource(makePK(c));
    }
    @Override
    public String getAttributIDName() {
        return "idRessource";
    }

    @Override
    public String getTuppleID() {
        return this.getIdRessource();
    }

    public String getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(String idRessource) {
        this.idRessource = idRessource;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public double getPuAchat() {
        return puAchat;
    }

    public void setPuAchat(double puAchat) {
        this.puAchat = puAchat;
    }
}