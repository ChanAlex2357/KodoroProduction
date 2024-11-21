package mg.kodoro.models.annexe;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;
import java.sql.Connection;

public class Produit extends MaClassMAPTable {
    private String idProduit;
    private String desce;
    private double puAchat;
    private double puVente;
    private int estAchat;
    private int estVente;
    private String idUnite;
    private String idTypeProduit;

    public Produit() {
        setNomTable("Produit");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("PRD", "GET_PRODUIT_SEQ");
        setIdProduit(makePK(c));
    }

    public Produit(String idProduit, String desce, double puAchat, double puVente, int estAchat, int estVente, String idUnite, String idTypeProduit) {
        this.idProduit = idProduit;
        this.desce = desce;
        this.puAchat = puAchat;
        this.puVente = puVente;
        this.estAchat = estAchat;
        this.estVente = estVente;
        this.idUnite = idUnite;
        this.idTypeProduit = idTypeProduit;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public double getPuAchat() {
        return puAchat;
    }

    public void setPuAchat(double puAchat) {
        this.puAchat = ValidationUtils.validatePositiveDouble(puAchat);
    }

    public double getPuVente() {
        return puVente;
    }

    public void setPuVente(double puVente) {
        this.puVente = ValidationUtils.validatePositiveDouble(puVente);
    }

    public int getEstAchat() {
        return estAchat;
    }

    public void setEstAchat(int estAchat) {
        this.estAchat = estAchat;
    }

    public int getEstVente() {
        return estVente;
    }

    public void setEstVente(int estVente) {
        this.estVente = estVente;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getIdTypeProduit() {
        return idTypeProduit;
    }

    public void setIdTypeProduit(String idTypeProduit) {
        this.idTypeProduit = idTypeProduit;
    }

    @Override
    public String getAttributIDName() {
        return "idProduit";
    }

    @Override
    public String getTuppleID() {
        return this.getIdProduit();
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("Produit");
        return super.createObject(c);
    }
}

