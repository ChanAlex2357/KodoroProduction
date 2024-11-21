package mg.kodoro.models.stock;

import java.sql.Connection;
import java.sql.Date;

import mg.kodoro.bean.MaClassMAPTable;

public class Inventaire extends MaClassMAPTable {
    private String idInventaire;
    private double quantite;
    private Date dateInventaire;
    private String idProduit;

    // Constructeur par défaut
    public Inventaire() {setNomTable("Inventaire");}
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("INV", "GET_INVENTAIRE_SEQ");
        setIdInventaire(makePK(c));

        System.out.println(this);
    }
    // Constructeur avec tous les paramètres sous forme de chaînes
    public Inventaire(String idInventaire, String quantite, String dateInventaire, String idProduit) {
        this.idInventaire = idInventaire;
        this.quantite = Double.parseDouble(quantite);
        this.dateInventaire = dateInventaire != null ? java.sql.Date.valueOf(dateInventaire) : null;
        this.idProduit = idProduit;
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("Inventaire");
        return super.createObject(c);
    }

    // Getters et Setters
    public String getIdInventaire() {
        return idInventaire;
    }

    public void setIdInventaire(String idInventaire) {
        this.idInventaire = idInventaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDateInventaire() {
        return dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        this.dateInventaire = dateInventaire;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "Inventaire{" +
                "idInventaire='" + idInventaire + '\'' +
                ", quantite=" + quantite +
                ", dateInventaire=" + dateInventaire +
                ", idProduit='" + idProduit + '\'' +
                '}';
    }

    @Override
    public String getAttributIDName() {
        return "idInventaire";
    }

    @Override
    public String getTuppleID() {
        return this.getIdInventaire();
    }
}
