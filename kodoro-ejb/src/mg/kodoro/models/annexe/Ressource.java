package mg.kodoro.models.annexe;

import java.sql.Connection;


import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.constant.KodoroConstantes;
import mg.kodoro.utils.ValidationUtils;

public class Ressource extends MaClassMAPTable{
    protected String idRessource;
    protected String desce;
    protected String idUnite;
    protected double puAchat;
    protected Unite unite;
    public Ressource () {setNomTable("Ressource");}
    public Ressource(String desce , String puAchat , String idUnite) {
        setDesce(desce);
        setPuAchat(puAchat);
        setIdUnite(idUnite);
    }

    private void setPuAchat(String puAchat) {
        setPuAchat( ValidationUtils.validatePositiveStringDouble(puAchat));
    }

    public static Ressource getById(String idRessource, Connection conn) throws Exception {
        Ressource ref = new Ressource();
        ref.setIdRessource(idRessource);
        Ressource[] ressources = (Ressource[])CGenUtil.rechercher(ref,null,null,conn,"");
        if (ressources.length < 0) {
            ressources = (Ressource[])CGenUtil.rechercher(new Ressource(),null,null,conn,"");
        }
        return ressources[0];
    }
    
    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("Ressource");
        genererProduit(c);
        return super.createObject(c);
    }
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
    public static Ressource[] getAllRessources(Connection conn) throws Exception {
        Ressource[] ressources = (Ressource[]) CGenUtil.rechercher(new Ressource() ,null , null , conn , "");
        return ressources.length<=0 ?  null:ressources;
    }

    public Produit genererProduit(Connection conn) throws Exception{
        Produit produit = getAsProduit();
        produit.createObject(conn);
        return produit;
    }
    public Produit getAsProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(this.getIdRessource());
        produit.setPuAchat(this.getPuAchat());
        produit.setEstAchat(1);
        produit.setDesce("Ressource - "+this.getDesce());
        produit.setIdTypeProduit(KodoroConstantes.TYPE_PRODUIT_RESSOURCE);
        produit.setIdUnite(this.getIdUnite());

        System.out.println(produit.getIdProduit());
        return produit;
    }
}
