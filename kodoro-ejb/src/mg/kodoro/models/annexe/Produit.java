package mg.kodoro.models.annexe;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.stock.Inventaire;
import utilitaire.Utilitaire;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;

import bean.CGenUtil;

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

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit='" + getIdProduit() + '\'' +
                ", desce =" + desce +
                ", type =" + idTypeProduit +
                ", unite ='" + idUnite + '\'' +
                '}';
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
        this.puAchat = puAchat;
    }

    public double getPuVente() {
        return puVente;
    }

    public void setPuVente(double puVente) {
        this.puVente = puVente;
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

    public void controllerDuplication(Connection conn) throws Exception {
        Produit p = Produit.getById(this.getIdProduit(), conn);
        if (p != null) {
            throw new Exception("Produit existant et ne peut pas etre dupliquer. ");
        }
    }
    @Override
    public Produit createObject(Connection c) throws Exception {
        setNomTable("Produit");
        controllerDuplication(c);
        super.createObject(c);
        System.out.println(this);
        genererInventaireInitiale(c);
        return this;
    }

    static public Produit getById(String idProduit,Connection conn) throws Exception {
        Produit ref = new Produit();
        ref.setIdProduit(idProduit);
        Produit[] produits = (Produit[]) CGenUtil.rechercher(ref,null , null , conn ,"");
        if (produits.length > 0 ) {
            return produits[0];
        }
        return null;
    }
    public Inventaire genererInventaireInitiale( Connection conn) throws Exception{
        Date datedujour = Utilitaire.dateDuJourSql();
        LocalDateTime localDateTime = datedujour.toLocalDate().atStartOfDay().minusDays(1L);
        Date datehier = Date.valueOf(localDateTime.toLocalDate());
        Inventaire inventaire = new Inventaire();
        inventaire.setIdProduit(this.getIdProduit());
        inventaire.setQuantite(0);
        inventaire.setDateInventaire(datehier);
        inventaire.createObject(conn);
        return inventaire;
    }
    public static Produit[] getAllProduits(Connection conn) throws Exception {
        Produit[] produits = (Produit[]) CGenUtil.rechercher(new Produit(),null , null , conn ,"");
        if (produits.length > 0) {
            return produits;
        }
        return null;
    }       
}

