package mg.kodoro.models.transformation;
import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.Bloc;
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.models.PrixMannagement;
import mg.kodoro.models.stock.MvtStockDimension;
import mg.kodoro.utils.ValidationUtils;

public class TransformationFille extends MaClassMAPTable implements PrixMannagement{
    private String idTransformationFille;
    private String idTransformation;
    private String idDimensionUsuels;
    private int    quantite;
    private double prixDeRevient;
    private double prixDeRevientUnitaire;

    
    protected TransformationLib transformation;
    protected MvtStockDimension mvtStockDimension;
    protected DimensionUsuels dimensionUsuels;
    
    // Constructeur par défaut
    public TransformationFille() {
        setNomTable("TRANSFORMATIONFILLE");
    }

    // Constructeur avec paramètres String
    public TransformationFille(String idDimension,String quantite,String prixDeRevientUnitaire,String prixDeRevient) {
        setIdDimensionUsuels(idDimension);
        setQuantite(quantite);
        setPrixDeRevientUnitaire(prixDeRevientUnitaire);
        setPrixDeRevient(prixDeRevient);
    }
    
    public double getPrixDeRevientUnitaire() {
        return prixDeRevientUnitaire;
    }

    public void setPrixDeRevientUnitaire(double prixDeRevientUnitaire) {
        this.prixDeRevientUnitaire = prixDeRevientUnitaire;
    }
    protected void setPrixDeRevient() {
        setPrixDeRevient( this.getPrixDeRevientUnitaire() * this.getQuantite());
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
        if (prixDeRevient == 0 &&  prixDeRevientUnitaire > 0 ) {
            setPrixDeRevient();
        }
        return prixDeRevient;
    }

    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public void setPrixDeRevient(String prixDeRevient) {
        try {
            this.setPrixDeRevient(ValidationUtils.validatePositiveStringDouble(prixDeRevient));
        } catch (IllegalArgumentException e) {
            if (this.getPrixDeRevientUnitaire() > 0) {
                setPrixDeRevient();
            }
        }
    }
    public void setPrixDeRevientUnitaire(String prixDeRevient) {
        try {
            this.setPrixDeRevientUnitaire(ValidationUtils.validatePositiveStringDouble(prixDeRevient));
        } catch (IllegalArgumentException e) {}
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

    public void controlerTransformation() throws Exception{
        if (this.getIdTransformation() == null || this.getIdTransformation().equals("0")) {
            throw new Exception("La transformation fille ["+this.getIdTransformationFille()+"] doit posseder un transformation mere ");
        }
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TRANSFORMATIONFILLE");
        // Controler la validiter de la transformation mere
        controlerTransformation();
        controlerPrixRevient();

        System.out.println(this);
        super.createObject(c);
        // generer le mouvement de stock
        this.genererMvtStockDimension(c);
        
        return this;
    }

    protected void controlerPrixRevient() {
        if (this.prixDeRevient == 0 && this.prixDeRevientUnitaire > 0) {
            setPrixDeRevient( this.getPrixDeRevientUnitaire() * this.getQuantite());
        }
        else if (getPrixDeRevientUnitaire() == 0 && getPrixDeRevient() >0) {
            setPrixDeRevientUnitaire(getPrixDeRevient() / this.getQuantite());
        }
    }

    public MvtStockDimension genererMvtStockDimension(Connection c) throws Exception{
        // Recuperer la transformation mere
        TransformationLib transformation = this.getTransformation(c);
        if (transformation == null) {
            throw new Exception("La transformation mere ["+this.getIdTransformation()+"] associee n'existe pas ou n'as pas ete instancier");
        }
        // Cree le mouvement de stock
        MvtStockDimension mvt = new MvtStockDimension();
        mvt.setIdDimensionUsuels(this.getIdDimensionUsuels());
        mvt.setIdOriginalSource( transformation.getIdOriginalSource());
        mvt.setEntree(this.getQuantite());
        mvt.setDaty(transformation.getDateFabrication());
        mvt.setSortie(0);
        mvt.setPrixDeRevientUnitaire(this.getPrixDeRevientUnitaire());
        mvt.setPrixDeRevient(this.getPrixDeRevient());
        mvt.setIdTransformationFille(this.getIdTransformationFille());
        // faire la persistance du mouvement de stock
        mvt.createObject(c);
        this.mvtStockDimension = mvt;
        return mvt;
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
            double vd = dim.getVolume(); 
            double v = fille.getVolume(conn);
            System.out.println( vd+" X "+fille.getQuantite()+" = "+v);
            somme += v;
        }
        return somme;
    }
            
    public DimensionUsuels getDimensionUsuels(Connection conn) throws Exception {
        if (this.dimensionUsuels != null) {
            return this.dimensionUsuels;
        }
        DimensionUsuels[] dimensions = new DimensionUsuels[1];
        dimensions[0] = new DimensionUsuels();
        dimensions[0].setIdDimensionUsuels(this.getIdDimensionUsuels());

        dimensions = (DimensionUsuels[]) CGenUtil.rechercher(dimensions[0],null,null,conn,"");

        if (dimensions.length > 0) {
            return dimensions[0];
        }
        return null;
    }

    public static double getSommeMontantVente(TransformationFilleLib[] details , Connection conn) throws Exception {
        double somme = 0;
        for (TransformationFilleLib transformationFille : details) {
            somme += transformationFille.getMontantVente(); 
        }
        return somme;
    }
    public static double getSommeMontantPrixDeRevient(TransformationFilleLib[] details, Connection conn) {
        double somme = 0;
        for (TransformationFilleLib transformationFille : details) {
            somme += transformationFille.getPrixDeRevient(); 
        }
        return somme;
    }

    

    @Override
    public void updatePrixDeRevient(double taux) {
        double newP = this.getPrixDeRevient() * taux;
        double newPu = this.getPrixDeRevientUnitaire() * taux;
        this.setPrixDeRevient(newP);
        this.setPrixDeRevientUnitaire(newPu);
    }

    @Override
    public void updatePrixDeRevient(double taux, Connection conn) throws Exception {
        setNomTable("TRANSFORMATIONFILLE");
        double pr = this.getPrixDeRevient();
        // Modifier le prix de revient et le prix de revient unitaire
        updatePrixDeRevient(taux);

        System.out.println(this.getIdTransformationFille()+" : "+pr+" => "+this.getPrixDeRevient());
        
        this.updateToTable(conn);
        // Mettre a jour le mouvement de stock
        MvtStockDimension mvt  = this.getMvtStockDimension(conn);
        mvt.updatePrixDeRevient(taux,conn);
    
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

    public static TransformationFilleLib[] getDetailsTransformationDimension(String idDimension , String idOrigine , Connection conn) throws Exception {
        TransformationFilleLib ref = new TransformationFilleLib();
        ref.setIdDimensionUsuels(idDimension);
        ref.setIdOriginalSource(idOrigine);

        TransformationFilleLib[] filles = (TransformationFilleLib[])CGenUtil.rechercher(ref,null,null,conn,"");
        if (filles.length > 0) {
            return filles;
        }
        return null;
    }

    public TransformationLib getTransformation(Connection conn) throws Exception{
        if (this.transformation != null) {
            return this.transformation;
        }
        TransformationLib tLib = new TransformationLib();
        tLib.setIdTransformation(this.getIdTransformation());
        TransformationLib[] transformations = (TransformationLib[])CGenUtil.rechercher(tLib,null,null,conn,"");
        if (transformations.length > 0 ) {
            this.transformation = transformations[0];
        }
        return this.transformation;
    }

    public MvtStockDimension getMvtStockDimension(Connection conn) throws Exception {
        if (this.mvtStockDimension != null) {
            return this.mvtStockDimension;
        }
        MvtStockDimension mvt = new MvtStockDimension();
        mvt.setIdTransformationFille(this.getIdTransformationFille());

        MvtStockDimension[] stock = (MvtStockDimension[]) CGenUtil.rechercher(mvt,null,null,conn,"");
        if (stock.length > 0) {
            return stock[0];
        }
        return null;
    }

    public double getVolume(Connection conn)throws Exception{
        DimensionUsuels dim = this.getDimensionUsuels(conn);
        return dim.getVolume() * this.getQuantite();
    }
    public void setPrixDeRevient(Bloc source ,Connection conn)throws Exception {
        double puv = source.getPrixUnitaireVolume();
        double volumeFille = this.getVolume(conn);
        this.setPrixDeRevient( volumeFille * puv);
    }
}
