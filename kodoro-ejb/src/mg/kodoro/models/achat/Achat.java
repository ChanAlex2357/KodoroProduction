package mg.kodoro.models.achat;

import java.sql.Date;
import mg.kodoro.bean.MaClassMAPTable;
import java.sql.Connection;

public class Achat extends MaClassMAPTable {
    private String idAchat;
    private double quantite;
    private Date dateAchat;
    private String idProduit;

    public Achat() {
        setNomTable("Achat");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("ACH", "GET_ACHAT_SEQ");
        setIdAchat(makePK(c));
    }
    public Achat(String idAchat, double quantite, Date dateAchat, String idProduit) {
        this.idAchat = idAchat;
        this.quantite = quantite;
        this.dateAchat = dateAchat;
        this.idProduit = idProduit;
    }

    public String getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(String idAchat) {
        this.idAchat = idAchat;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String getAttributIDName() {
        return "idAchat";
    }

    @Override
    public String getTuppleID() {
        return this.getIdAchat();
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("Achat");
        return super.createObject(c);
    }
}

