package mg.kodoro.models.transformation;
import java.sql.Connection;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;

public class TransformationFille extends MaClassMAPTable {
    private String idTransformationFille;
    private String idDimensionUsuels;
    private int quantite;
    private double prixDeRevient;

    // Constructeur par défaut
    public TransformationFille() {}

    // Constructeur avec paramètres double
    public TransformationFille( int quantite, double prixDeRevient) {
        setQuantite(quantite);
        setPrixDeRevient(prixDeRevient);
    }

    // Constructeur avec paramètres String
    public TransformationFille( String quantite, String prixDeRevient) {
        setQuantite(quantite);
        setPrixDeRevient(prixDeRevient);
    }

    // Getters et Setters
    public String getIdTransformationFille() {
        return idTransformationFille;
    }

    public void setIdTransformationFille(String idTransformationFille) {
        this.idTransformationFille = idTransformationFille;
    }

    public String getIdDimensionUsuels() {
        return idDimensionUsuels;
    }

    public void setIdDimensionUsuels(String idDimensionUsuels) {
        this.idDimensionUsuels = idDimensionUsuels;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = ValidationUtils.validatePositiveStringInt(quantite);
    }

    public double getPrixDeRevient() {
        return prixDeRevient;
    }

    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public void setPrixDeRevient(String prixDeRevient) {
        this.prixDeRevient = ValidationUtils.validatePositiveStringDouble(prixDeRevient);
    }

    @Override
    public String toString() {
        return "TransformationFille{" +
                "idTransformationFille='" + idTransformationFille + '\'' +
                ", idDimensionUsuels='" + idDimensionUsuels + '\'' +
                ", quantite=" + quantite +
                ", prixDeRevient=" + prixDeRevient +
                '}';
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        return this;
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TRANSFORMATIONFILLE");
        if (this.getTuppleID() == null || this.getTuppleID().isEmpty() || this.getTuppleID().equals("0")) {
            this.construirePK(c);
        }
        
        System.out.println(this);
        return super.createObject(c);
    }

    @Override
    public String getAttributIDName() {
        return "idTransformationFille";
    }

    @Override
    public String getTuppleID() {
        return getIdTransformationFille();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("TRANSFILLE", "GET_TRANSFILLE_SEQ");
        this.setIdTransformationFille(makePK(c));
    }
}
