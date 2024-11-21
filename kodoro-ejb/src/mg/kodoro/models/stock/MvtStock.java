package mg.kodoro.models.stock;

import java.sql.Date;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;

import java.sql.Connection;

public class MvtStock extends MaClassMAPTable {
    private String idMvtStock;
    private double entree;
    private double sortie;
    private Date dateMvtStock;
    private String idVente;
    private String idAchat;
    private double puVente;
    private double puAchat;
    private String idProduit;
    private String idTypeMvt;
    private String idTransfert;
    private String motif;
    public MvtStock() {
        setNomTable("MvtStock");
    }

    public MvtStock(String idMvtStock, String entree, String sortie, Date dateMvtStock, String idVente, String idAchat, double puVente, double puAchat, String idProduit, String idTypeMvt , String idTransfert , String motif) {
        this.idMvtStock = idMvtStock;
        this.entree = ValidationUtils.validatePositiveDouble(puAchat);
        this.sortie = ValidationUtils.validatePositiveDouble(puAchat);
        this.dateMvtStock = dateMvtStock;
        this.idVente = idVente;
        this.idAchat = idAchat;
        this.puVente = puVente;
        this.puAchat = puAchat;
        this.idProduit = idProduit;
        this.idTypeMvt = idTypeMvt;
        setMotif(motif);
        setIdTransfert(idTransfert);
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("MST", "GET_MVT_STOCK_SEQ");
        setIdMvtStock(makePK(c));
    }

    public String getIdMvtStock() {
        return idMvtStock;
    }

    public void setIdMvtStock(String idMvtStock) {
        this.idMvtStock = idMvtStock;
    }

    public double getEntree() {
        return entree;
    }

    public void setEntree(double entree) {
        this.entree = entree;
    }

    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public Date getDateMvtStock() {
        return dateMvtStock;
    }

    public void setDateMvtStock(Date dateMvtStock) {
        this.dateMvtStock = dateMvtStock;
    }

    public String getIdVente() {
        return idVente;
    }

    public void setIdVente(String idVente) {
        this.idVente = idVente;
    }

    public String getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(String idAchat) {
        this.idAchat = idAchat;
    }

    public double getPuVente() {
        return puVente;
    }

    public void setPuVente(double puVente) {
        this.puVente = puVente;
    }

    public double getPuAchat() {
        return puAchat;
    }

    public void setPuAchat(double puAchat) {
        this.puAchat = puAchat;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getIdTypeMvt() {
        return idTypeMvt;
    }

    public void setIdTypeMvt(String idTypeMvt) {
        this.idTypeMvt = idTypeMvt;
    }
    
    public String getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(String idTransfert) {
        this.idTransfert = idTransfert;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String getAttributIDName() {
        return "idMvtStock";
    }

    @Override
    public String getTuppleID() {
        return this.getIdMvtStock();
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("MvtStock");
        return super.createObject(c);
    }


}
