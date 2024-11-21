package mg.kodoro.models.achat;

import java.sql.Date;
import java.text.ParseException;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.constant.KodoroConstantes;
import mg.kodoro.models.stock.MvtStock;
import mg.kodoro.utils.ValidationUtils;
import utils.TimeUtils;

import java.sql.Connection;

public class Achat extends MaClassMAPTable {
    private String idAchat;
    private double quantite;
    private double puAchat;
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
    public Achat (String idProduit , String quantite,String puAchat, String dateAchat) throws Exception{
        setIdProduit(idProduit);
        setDateAchat(dateAchat);
        setQuantite(quantite);
        setPuAchat(puAchat);
    }
    private void setPuAchat(String puAchat2) {
        setPuAchat( ValidationUtils.validatePositiveStringDouble(puAchat2));
    }
    private void setQuantite(String quantitee) {
        setQuantite( ValidationUtils.validatePositiveStringDouble(quantitee));
    }
    private void setDateAchat(String dateAchate) throws ParseException {
        setDateAchat( TimeUtils.convertToSqlDate(dateAchate, "eng"));
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
        genererMvtStock(c);
        return super.createObject(c);
    }

    public MvtStock genererMvtStock(Connection conn) throws Exception{
        MvtStock mvt = new MvtStock();
        mvt.setIdAchat(idAchat);
        mvt.setIdProduit(idProduit);
        mvt.setDateMvtStock(dateAchat);
        mvt.setEntree(quantite);
        mvt.setSortie(0);
        mvt.setIdTypeMvt(KodoroConstantes.TYPE_MVT_ENTREE);
        mvt.setPuAchat(puAchat);

        mvt.createObject(conn);
        return mvt;
    }
    public double getPuAchat() {
        return puAchat;
    }
    public void setPuAchat(double puAchat) {
        this.puAchat = puAchat;
    }
}

