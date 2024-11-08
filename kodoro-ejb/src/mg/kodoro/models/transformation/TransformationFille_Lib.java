package mg.kodoro.models.transformation;

import java.sql.Date;

public class TransformationFille_Lib extends TransformationFille{
    protected Date dateTransformation;
    protected String idBloc;
    protected double marge;
    protected String desce ;
    protected String idOriginalSource;
    protected String idParentSource;

    public TransformationFille_Lib(){
        setNomTable("TransformationFille_Lib");
    }

    public Date getDateTransformation() {
        return dateTransformation;
    }
    public void setDateTransformation(Date dateTransformation) {
        this.dateTransformation = dateTransformation;
    }
    public String getIdBloc() {
        return idBloc;
    }
    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
    }
    public double getMarge() {
        return marge;
    }
    public void setMarge(double marge) {
        this.marge = marge;
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
        return "TransforamtionFille_LIB {" +
                "idTransformationFille='" + getIdTransformationFille() + '\'' +
                ", idDimensionUsuels='" + getIdDimensionUsuels() + '\'' +
                ", quantite=" + getQuantite() +
                ", prixDeRevient=" + getPrixDeRevient() +
                ", idBloc='" + getIdBloc() + '\'' +
                ", marge=" + getMarge() +
                ", idTransformation='" + getIdTransformation() + '\'' +
                ", dateTransformation=" + getDateTransformation()+
                ", desce='" + desce + '\'' +
                ", idOriginalSource='" + idOriginalSource + '\'' +
                ", idParentSource='" + idParentSource + '\'' +
                '}';
    }
    
}
