package mg.kodoro.models.transformation;
import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.Bloc;
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.utils.ValidationUtils;

public class TransformationFille extends MaClassMAPTable {
    private String idTransformationFille;
    private String idTransformation;
    private String idDimensionUsuels;
    private int quantite;
    private double prixDeRevient;
    private double prixVente;

    protected Transformation transformation;
    
    // Constructeur par défaut
    public TransformationFille() {
        setNomTable("TRANSFORMATIONFILLE");
    }
    
    // Constructeur avec paramètres double
    public TransformationFille( String idDimension,int quantite, double prixDeRevient) {
        setIdDimensionUsuels(idDimension);
        setQuantite(quantite);
        setPrixDeRevient(prixDeRevient);
    }

    // Constructeur avec paramètres String
    public TransformationFille(String idDimension,String prixVente,String quantite,String prixDeRevient) {
        setIdDimensionUsuels(idDimension);
        setPrixVente(prixVente);
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
    public String getIdTransformation() {
        return idTransformation;
    }

    public void setIdTransformation(String idTransformation) {
        this.idTransformation = idTransformation;
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
    public void setPrixVente(String prixDeRevient) {
        this.prixVente = ValidationUtils.validatePositiveStringDouble(prixDeRevient);
    }

    public double getMontantVente() throws Exception{
        return this.getPrixVente() * this.getQuantite();
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
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

    public void controllerTransformation() throws Exception{
        if (this.getIdTransformation() == null || this.getIdTransformation().equals("0")) {
            throw new Exception("La transformation fille ["+this.getIdTransformationFille()+"] doit posseder un transformation mere ");
        }
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        return this;
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TRANSFORMATIONFILLE");
        // Controller la validiter de la transformation mere
        controllerTransformation();
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
        preparePk("TRANSF", "GET_TRANSF_SEQ");
        this.setIdTransformationFille(makePK(c));
    }

    public static double getSommeVolume(TransformationFille[] filles , Connection conn) throws Exception{
        double somme = 0;
        for(TransformationFille fille : filles){
            DimensionUsuels dim = fille.getDimensionUsuels(conn);
            System.out.println(dim); 
            double v = dim.getVolume() * fille.getQuantite();
            System.out.println(v);
            somme += v;
        }
        return somme;
    }
            
    public DimensionUsuels getDimensionUsuels(Connection conn) throws Exception {
        DimensionUsuels[] dimensions = new DimensionUsuels[1];
        dimensions[0] = new DimensionUsuels();
        dimensions[0].setIdDimensionUsuels(this.getIdDimensionUsuels());

        dimensions = (DimensionUsuels[]) CGenUtil.rechercher(dimensions[0],null,null,conn,"");

        if (dimensions.length > 0) {
            return dimensions[0];
        }
        return null;
    }

    public static double getSommeMontantVente(TransformationFille[] details , Connection conn) throws Exception {
        double somme = 0;
        for (TransformationFille transformationFille : details) {
            somme += transformationFille.getMontantVente(); 
        }
        return somme;
    }

    public void updatePrixDeRevient(double taux, Connection conn) throws Exception {
        double newP = this.getPrixDeRevient() * taux;
        System.err.println(this.getIdTransformationFille()+" : "+this.getPrixDeRevient()+" => "+newP);
        this.setPrixDeRevient(newP);
        this.updateToTable(conn);
    }

    public static TransformationFilleLib getByIdTransformation(String idTransformation , Connection conn) throws Exception {
        TransformationFilleLib ref = new TransformationFilleLib();
        ref.setIdTransformation(idTransformation);

        TransformationFilleLib[] transformationFille_Libs = (TransformationFilleLib[]) CGenUtil.rechercher(ref, null , null , conn , "");

        if (transformationFille_Libs.length > 0) {
            return transformationFille_Libs[0];
        }
        return null;
    }
    public static TransformationFilleLib getById(String id , Connection conn) throws Exception {
        TransformationFilleLib ref = new TransformationFilleLib();
        ref.setIdTransformationFille(id);

        TransformationFilleLib[] transformationFille_Libs = (TransformationFilleLib[]) CGenUtil.rechercher(ref, null , null , conn , "");

        if (transformationFille_Libs.length > 0) {
            return transformationFille_Libs[0];
        }
        return null;
    }

    public TransformationLib getTransformation(Connection conn) throws Exception{
        TransformationLib tLib = new TransformationLib();
        tLib.setIdTransformation(this.getIdTransformation());

        TransformationLib[] transformations = (TransformationLib[])CGenUtil.rechercher(tLib,null,null,conn,"");
        if (transformations.length > 0 ) {
            return transformations[0];
        }
        return null;
    }
}
