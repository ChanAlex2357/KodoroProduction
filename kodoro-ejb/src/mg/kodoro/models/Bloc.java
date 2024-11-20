package mg.kodoro.models;

import java.sql.Date;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.models.blockggen.DataBloc;
import mg.kodoro.models.dimension.ClassDimension;
import mg.kodoro.models.production.Production;
import mg.kodoro.models.pricing.PrixMannagement;
import mg.kodoro.models.transformation.TransformationLib;
import mg.kodoro.utils.ValidationUtils;
import mg.kodoro.utils.blockggen.BlocStat;
import mg.kodoro.utils.blockggen.GenerateurBloc;
import utilitaire.UtilDB;
import utils.TimeUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bloc extends ClassDimension implements PrixMannagement{
    private String idBloc;
    private String desce;
    private Date   dateFabrication;
    private double prixFabrication;
    private String idOriginalSource;
    private String idParentSource;
    protected Bloc originalSource;
    protected Bloc parentSource;
    protected TransformationLib[] transformations;
    protected Bloc[] restes ;
    protected Production production;
    protected static BlocStat blocStat;
    
    public static BlocStat getBlocStat(Connection conn) throws Exception {
        if (Bloc.blocStat == null) {
            Bloc.setBlocStat(BlocStat.getBlocStat(conn));
        }
        return Bloc.blocStat;
    }

    protected static void setBlocStat(BlocStat blocStat) {
        Bloc.blocStat = blocStat;
    }

    public String getDesceVolume(){
        return this.getIdBloc()+" - "+this.getVolume()+" m³";
    }

    @Override
public String toString() {
    return "Bloc{" +
            "idBloc='" + idBloc + '\'' +
            ", description='" + desce + '\'' +
            ", longueur=" + longueur +
            ", largeur=" + largeur +
            ", epaisseur=" + epaisseur +
            ", volume=" + this.getVolume() +
            ", dateFabrication=" + (dateFabrication != null ? new SimpleDateFormat("yyyy-MM-dd").format(dateFabrication) : "null") +
            ", prixFabrication=" + prixFabrication +
            ", idOriginalSource='" + idOriginalSource + '\'' +
            ", idParentSource='" + idParentSource + '\'' +
            '}';
}

    public Bloc(){
        setNomTable("BLOC");
    }

    public Bloc(GenerateurBloc gen, Connection conn) throws Exception {
        setNomTable("BLOC");
        setLongueur(gen.genererLongueur());
        setLargeur(gen.genererLargeur());
        setEpaisseur(gen.genererEpaisseur());
        setDateFabrication(gen.generateDateFabrication());
        setPrixFabrication(gen.genererPrixDeRevient(conn));
    }
    
    public Bloc (String longueur , String largeur ,String epasseur , String dateFab , String prixFab) throws ParseException{
        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epasseur);
        setDateFabrication(dateFab);
        setPrixFabrication(prixFab);
    }

    // Getters and Setters
    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }
    
    public void setLongueur(double longueur) {
        longueur = ValidationUtils.validatePositiveDouble(longueur);
        this.longueur = longueur;
    }
    
    public void setLargeur(double largeur) {
        largeur = ValidationUtils.validatePositiveDouble(largeur);
        this.largeur = largeur;
    }


    public void setEpaisseur(double epaisseur) {
        epaisseur = ValidationUtils.validatePositiveDouble(epaisseur);
        this.epaisseur = epaisseur;
    }


    public void setDateFabrication(Date dateFabrication) {
        if (dateFabrication == null) {
            throw new IllegalArgumentException("La date de fabrication ne peut pas etre null");
        }
        this.dateFabrication = dateFabrication;
    }


    public void setPrixFabrication(double prixFabrication) {
        prixFabrication = ValidationUtils.validatePositiveDouble(prixFabrication);
        this.prixFabrication = prixFabrication;
    } 
    public String getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
    }

    public Date getDateFabrication() {
        return this.dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) throws ParseException {
        if (dateFabrication == null) {
            throw new IllegalArgumentException ("Veuillez saisir une date valide");
        }
        System.out.println("DATE FABRICATION : "+dateFabrication);
        setDateFabrication(TimeUtils.convertToSqlDate(dateFabrication,"eng"));
    }
    public double getPrixFabrication() {
        return prixFabrication;
    }

    public static Bloc getById(String idBloc,Connection conn) throws Exception{
        Bloc b = new Bloc();
        b.setIdBloc(idBloc);
        System.out.println("---- GETTING BLOC : "+idBloc+" ----------");
        Bloc[] blocs =  (Bloc[])CGenUtil.rechercher(b,null,null,conn,"");
        if (blocs.length <=0 ) {
            return null;
        }
        return blocs[0];
    }

    public void setPrixFabrication(String prixFabrication) {
        this.prixFabrication = ValidationUtils.validatePositiveStringDouble(prixFabrication);
    }

    public String getIdOriginalSource() {
        return idOriginalSource;
    }

    public void setIdOriginalSource(String originalSource) {
        this.idOriginalSource = originalSource;
    }
    public void setIdOriginalSource(Bloc bloc) {
        if (bloc.getIdOriginalSource() != null) {
            System.out.println("Orignal Existe : "+bloc.getIdOriginalSource());
            setIdOriginalSource(bloc.getIdOriginalSource());
            return ;
        }
        System.out.println("BLOC ORIGINAL");
        setIdOriginalSource(bloc.getIdBloc());
    }

    public String getIdParentSource() {
        return idParentSource;
    }

    public void setIdParentSource(String parentSource) {
        this.idParentSource = parentSource;
    }

    @Override
    public String getAttributIDName() {
        return "idBloc";
    }

    @Override
    public String getTuppleID() {
        return this.getIdBloc();
    }


    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("BLC", "GET_BLOC_SEQ");
        this.setIdBloc( makePK(c) );
    }
    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("BLOC");
        controlerTaille();
        controlerVolume();
        if (this.getTuppleID() == null || this.getTuppleID().compareToIgnoreCase("") == 0 || this.getTuppleID().compareToIgnoreCase("0") == 0) {
            this.construirePK(c);
        } 
        if (desce == null) {
            this.setDesce( "Fabrication du bloc "+this.getIdBloc()+"["+this.getLongueur()+" x "+this.getLargeur()+" x "+this.getEpaisseur()+"]");
        }
        System.out.println(this);
        return super.createObject(c);
    }

    private void controlerVolume() {
        if (this.volume <= 0 ) {
            this.getVolume();
        }
    }
        
    public void controlerTaille() throws Exception{
        if (this.getLongueur() <= this.getLargeur() ) {
            throw new Exception("La taille du bloc est invalide ; La longueur doit être supérieure à la largeur.");
        }
    }
    

    static public Bloc[] getAllBlocs(){
        Bloc[] blocs = new Bloc[0];
        Connection conn = new UtilDB().GetConn();
        try {
            blocs = (Bloc[]) CGenUtil.rechercher(new Bloc() , null , null , conn , "");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blocs;
    }
    static public Bloc[] getAllBlocOriginal(){
        Bloc[] blocs = new Bloc[0];
        Connection conn = new UtilDB().GetConn();
        try {
            blocs = (Bloc[]) CGenUtil.rechercher(new Bloc() , null , null , conn , " and (idparentsource is null and idoriginalsource is null) ");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blocs;
    }
    public double getPrixUnitaireVolume(){
        System.out.println(this);
        double prix = this.getPrixFabrication();
        double v = this.getVolume();
        System.out.println("PF : "+prix);
        System.out.println("VV : "+v);
        return prix / v;
    }


    public Bloc getBlocParentSource(Connection conn) throws Exception{
        if (this.parentSource == null) {
            this.parentSource = Bloc.getById(this.getIdParentSource(), conn);
        }
        return this.parentSource;
    }

    public Bloc getBlocOriginalSource(Connection conn) throws Exception{
        if (this.originalSource == null) {
            this.originalSource = Bloc.getById(this.getIdOriginalSource(), conn);
        }
        return this.originalSource;
    }

    public void estimatePrixFabrication(Bloc blocSource , Connection conn) throws Exception{
        if (blocSource != null) {
            System.out.println("BLOC SOURCE ESTIMATION : "+blocSource.getIdBloc());
        }
        if (blocSource == null || !blocSource.getIdBloc().equals(this.getIdParentSource())) {
            blocSource = getBlocParentSource(conn);
            System.out.println("RECUPERATION SOURCE VRAIE : "+blocSource.getIdBloc());
        }

        double puv = blocSource.getPrixUnitaireVolume();
        double volume = this.getVolume();

        System.out.println("PUV : "+puv);
        System.out.println("VOLUME RESTE : "+volume);

        double prixFab =  puv * volume;

        if (blocSource.getPrixFabrication() < prixFab) {
            throw new Exception("Le prix de fabrication d'un bloc estimer ne peut pas surpasser son prix de fabrication de base. ");
        }
        System.out.println("PRIX FAB : "+prixFab);
        this.setPrixFabrication(prixFab);
    }

    public static double getSommeVolume(Bloc[] blocs){
        double somme = 0;
        for(Bloc bloc : blocs){
            somme += bloc.getVolume();
        }
        return somme;
    }

    public Bloc[] getRestes(Connection conn) throws Exception{
        if (this.restes != null) {
            return this.restes;
        }
        Bloc[] blocs = (Bloc[]) CGenUtil.rechercher(new Bloc(),null , null , conn , " and (idbloc not in ( select idbloc from v_blocmere ) and idoriginalsource = '"+this.getIdBloc()+"') ");
        if (blocs.length <= 0) {
            blocs = new Bloc[1];
            blocs[0] = this;
        }
        return blocs;
    }
    public TransformationLib[] getTransformations(Connection conn) throws Exception{
        if (this.transformations != null) {
            return this.transformations;
        }
        TransformationLib[] tLib = (TransformationLib[]) CGenUtil.rechercher(new TransformationLib(),null,null,conn," AND (idBloc = '"+this.getIdBloc()+"' OR idOriginalSource = '"+this.getIdBloc()+"')");

        if (tLib.length <= 0 ) {
            return new TransformationLib[0];
        }
        setTransformations(tLib);
        return this.transformations;
    }
    public double getEstimationPrixDeRevientTotal(Connection conn)throws Exception{
        TransformationLib[] transformations = getTransformations(conn);
        double somme = TransformationLib.getSommePrixDeRevient(transformations, conn);
        return somme;
    }
    public double getEstimationVente(Connection conn)throws Exception{
        TransformationLib[] transformations = getTransformations(conn);
        double sommeVente = TransformationLib.getSommeMontantVente(transformations, conn);
        return sommeVente;
    }

    public Bloc getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(Bloc originalSource) {
        this.originalSource = originalSource;
    }

    public Bloc getParentSource() {
        return parentSource;
    }

    public void setParentSource(Bloc parentSource) {
        this.parentSource = parentSource;
    }

    public TransformationLib[] getTransformations() {
        return transformations;
    }

    private void setTransformations(TransformationLib[] transformations) {
        this.transformations = transformations;
    }

    public Bloc[] getFille(Connection conn) throws Exception {
        Bloc bref = new Bloc();
        bref.setIdOriginalSource( this.getIdBloc() );
        Bloc[] filles = (Bloc[])CGenUtil.rechercher(bref,null,null,conn,"");
        if (filles.length <= 0) {
            return new Bloc[0];
        }
        return filles;
    }

    public double getTauxChangePrixFab( double newPrixFab ){
        return newPrixFab / this.getPrixFabrication(); 
    }
    public void updatePrixFabrication( double tauxChange ){
        double newFab = this.getPrixFabrication() * tauxChange;
        System.out.println(this.getIdBloc()+" : "+this.getPrixFabrication()+" => "+newFab);
        this.setPrixFabrication( newFab);
    }
    public void updatePrixFabrication(double prixFab , Connection conn) throws Exception {
        System.out.println( " | UPDATE PRIX DE FABRICATION |"+this.getIdBloc());
        double taux = this.getTauxChangePrixFab(prixFab);
        System.out.println("TAUX DE CAHNGE : "+taux);
        this.setPrixFabrication(prixFab);
        // Mettre la table a jour
        updateToTable(conn);
        // Mettre a jour les blocs filles
        /// recuperer la liste des filles
        Bloc[] filles = this.getFille(conn);
        System.out.println("-- UPDATE PRIX DE FABRICATION FILLE----");
        for (Bloc bloc : filles) {
            bloc.updatePrixFabrication(taux);  // Changer le priix selon le taux
            bloc.updateToTable(conn); // update la table
        }
        System.out.println("--- --- --- --- --- ---");

        // Mettre a jour les prix de transformation
        TransformationLib[] trans = this.getTransformations(conn);
        System.out.println("-- UPDATE PRIX DE REVIENT DE TRANSFORMATION ----");
        for (TransformationLib transformationLib : trans) {
            transformationLib.updatePrixDeRevientTransformation(taux, conn);
        }
        System.out.println("--- --- --- --- --- ---");


        /// Mettre a jour la production associe
        Production prod = getProduction(conn);
        prod.updatePrPratique(conn);


    }

    public void updatePrixFabrication(String prixFab , Connection conn) throws Exception {
        updatePrixFabrication( ValidationUtils.validatePositiveStringDouble(prixFab) , conn);
    }

    @Override
    public void updatePrixDeRevient(double tauxDeChange) {
        this.updatePrixFabrication(tauxDeChange);
    }

    @Override
    public void updatePrixDeRevient(double newPrix, Connection conn) throws Exception {
        this.updatePrixFabrication(newPrix, conn);
    }

    public Production getProduction(Connection conn) throws Exception {
        if (this.production != null) {
            return this.production;
        }

        Production ref = new Production();
        ref.setIdBloc(this.getIdBloc());
        Production [] prod = (Production[]) CGenUtil.rechercher(ref,null,null,conn,"");
        if (prod.length > 0) {
            prod[0].setBlocProduit(this);
            return prod[0];
        }
        return null;
    }

    public static Bloc randomBLoc(Connection conn) throws Exception{
        DataBloc dataBloc = new DataBloc();
        GenerateurBloc gen = new GenerateurBloc(dataBloc);
        Bloc bloc = new Bloc(gen, conn);
        return bloc;
    }

}
