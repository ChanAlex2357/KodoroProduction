package mg.kodoro.models;

import java.sql.Date;

import bean.CGenUtil;
import bean.ClassMAPTable;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;
import utilitaire.UtilDB;
import utils.TimeUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bloc extends MaClassMAPTable{
    private String idBloc;
    private String desce;
    private double longueur;
    private double largeur;
    private double epaisseur;
    private Date dateFabrication;
    private double prixFabrication;
    private String idOriginalSource;
    private String idParentSource;
    @Override
public String toString() {
    return "Bloc{" +
            "idBloc='" + idBloc + '\'' +
            ", description='" + desce + '\'' +
            ", longueur=" + longueur +
            ", largeur=" + largeur +
            ", epaisseur=" + epaisseur +
            ", dateFabrication=" + (dateFabrication != null ? new SimpleDateFormat("yyyy-MM-dd").format(dateFabrication) : "null") +
            ", prixFabrication=" + prixFabrication +
            ", idOriginalSource='" + idOriginalSource + '\'' +
            ", idParentSource='" + idParentSource + '\'' +
            '}';
}

    public Bloc(){
        setNomTable("BLOC");
    }
    
    public Bloc (String longueur , String largeur ,String epasseur , String dateFab , String prixFab) throws ParseException{
        setNomTable("BLOC");

        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epasseur);
        setDateFabrication(dateFab);
        setPrixFabrication(prixFab);
    }
    public Bloc(double longueur , double largeur , double epaisseur , Date dateFab , double prixFab){
        setNomTable("BLOC");
        
        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epaisseur);
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

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        if (longueur == null) {
            throw new IllegalArgumentException("Veuillez saisir une valeur de longueur valide");
        }
        this.setLongueur(ValidationUtils.validatePositiveStringDouble(longueur));
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        if (largeur == null) {
            throw new IllegalArgumentException("Veuillez saisir une valeur de largeur valide");
        }
        this.setLargeur(ValidationUtils.validatePositiveStringDouble(largeur));
    }

    public double getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(String epaisseur) {
        if (epaisseur == null){
            throw new IllegalArgumentException("Veuillez saisir une valeur d'eppaisseur valide");
        }
        setEpaisseur(ValidationUtils.validatePositiveStringDouble(epaisseur));
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
            setIdOriginalSource(bloc.getIdOriginalSource());
            return ;
        }
        setIdOriginalSource(bloc.getIdBloc());
    }

    public String getIdParentSource() {
        return idParentSource;
    }

    public void setIdParentSource(String parentSource) {
        this.idParentSource = parentSource;
    }

    // Calculate volume
    public double getVolume() {
        return longueur * largeur * epaisseur;
    }



    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        this.createObject(localconn);
        //! Generer le mouvement de stock dans la base distante
        return this;
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
        controlerTaille();
        if (this.getTuppleID() == null || this.getTuppleID().compareToIgnoreCase("") == 0 || this.getTuppleID().compareToIgnoreCase("0") == 0) {
            this.construirePK(c);
        } 
        if (desce == null) {
            this.setDesce( "Fabrication du bloc "+this.getIdBloc()+"["+this.getLongueur()+" x "+this.getLargeur()+" x "+this.getEpaisseur()+"]");
        }
        System.out.println(this);
        return super.createObject(c);
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
    public double getPrixUnitaireVolume(){
        return this.getPrixFabrication() / getVolume();
    }


    public Bloc getBlocParentSource(Connection conn) throws Exception{
        return Bloc.getById(this.getIdParentSource(), conn);
    }

    public Bloc getBlocOriginalSource(Connection conn) throws Exception{
        return Bloc.getById(this.getIdOriginalSource(), conn);
    }

    public void estimatePrixFabrication(Bloc blocSource , Connection conn) throws Exception{
        if (blocSource == null || !blocSource.getIdBloc().equals(this.getIdParentSource())) {
            blocSource = getBlocParentSource(conn);
        }

        double puv = blocSource.getPrixUnitaireVolume();
        double volume = this.getVolume();

        double prixFab =  puv * volume;

        if (this.getPrixFabrication() < prixFab) {
            throw new Exception("Le prix de fabrication d'un bloc estimer ne peut pas surpasser son prix de fabrication de base. ");
        }
        this.setPrixFabrication(prixFab);
    }

    public static double getSommeVolume(Bloc[] blocs){
        double somme = 0;
        for(Bloc bloc : blocs){
            somme += bloc.getVolume();
        }
        return somme;
    }
}
