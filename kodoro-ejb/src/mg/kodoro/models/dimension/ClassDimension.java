package mg.kodoro.models.dimension;

import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;

public abstract class ClassDimension extends MaClassMAPTable{
    protected double longueur;
    protected double largeur;
    protected double epaisseur;
    protected double volume;
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
    public double getVolume() {
        if (this.volume <= 0) {
            setVolume(getLongueur() * getLargeur() * getEpaisseur());
        }
        return this.volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setLongueur(String longueur) {
        if (longueur == null) {
            throw new IllegalArgumentException("Veuillez saisir une valeur de longueur valide");
        }
        this.setLongueur(ValidationUtils.validatePositiveStringDouble(longueur));
    }

    public void setLargeur(String largeur) {
        if (largeur == null) {
            throw new IllegalArgumentException("Veuillez saisir une valeur de largeur valide");
        }
        this.setLargeur(ValidationUtils.validatePositiveStringDouble(largeur));
    }

    public void setEpaisseur(String epaisseur) {
        if (epaisseur == null){
            throw new IllegalArgumentException("Veuillez saisir une valeur d'eppaisseur valide");
        }
        setEpaisseur(ValidationUtils.validatePositiveStringDouble(epaisseur));
    }
}
