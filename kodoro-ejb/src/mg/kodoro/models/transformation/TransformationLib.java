package mg.kodoro.models.transformation;

import java.util.Date;

public class TransformationLib extends Transformation {
    private double longueur;
    private double largeur;
    private double epaisseur;
    private double prixFabrication;
    private Date dateFabrication;
    private String desce;
    private String idOriginalSource;
    private String idParentSource;
    public TransformationLib( ){
        setNomTable("Transformation_Lib");
    }

    // Getters et Setters pour les nouveaux attributs

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

    public double getPrixFabrication() {
        return prixFabrication;
    }

    public void setPrixFabrication(double prixFabrication) {
        this.prixFabrication = prixFabrication;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    public String getIdOriginalSource() {
        return idOriginalSource;
    }

    public void setIdOriginalSource(String idOriginalSource) {
        this.idOriginalSource = idOriginalSource;
    }

    public String getIdParentSource() {
        return idParentSource;
    }

    public void setIdParentSource(String idParentSource) {
        this.idParentSource = idParentSource;
    }

    // Redéfinir la méthode toString() pour faciliter l'affichage
    @Override
    public String toString() {
        return "TransformationLib{" +
                "idTransformation='" + getIdTransformation() + '\'' +
                ", idBloc='" + getIdBloc() + '\'' +
                ", marge=" + getMarge() +
                ", dateTransformation=" + getDateTransformation() +
                ", longueur=" + longueur +
                ", largeur=" + largeur +
                ", epaisseur=" + epaisseur +
                ", prixFabrication=" + prixFabrication +
                ", dateFabrication=" + dateFabrication +
                ", desce='" + desce + '\'' +
                ", idOriginalSource='" + idOriginalSource + '\'' +
                ", idParentSource='" + idParentSource + '\'' +
                '}';
    }



}