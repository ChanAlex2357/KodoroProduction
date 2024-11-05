package mg.kodoro.bloc;

import java.util.Date;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;
import mg.kodoro.utils.ValidationUtils;
import utilitaire.UtilDB;
import utils.TimeUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Bloc extends MaClassMAPTable{
    private String idBloc;
    
    private double longueur;
    private double largeur;
    private double epaisseur;
    private Date dateFabrication;
    private double prixFabrication;
    private String originalSource;
    private String parentSource;
    public Bloc(){}

    public Bloc (String longueur , String largeur ,String epasseur , String dateFab , String prixFab) throws ParseException{
        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epasseur);
        setDateFabrication(dateFab);
        setPrixFabrication(prixFab);
    }
    public Bloc(double longueur , double largeur , double epaisseur , Date dateFab , double prixFab){
        setLongueur(longueur);
        setLargeur(largeur);
        setEpaisseur(epaisseur);
        setDateFabrication(dateFab);
        setPrixFabrication(prixFab);
    }
    // Getters and Setters
    
    
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
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) throws ParseException {
        if (dateFabrication == null) {
            throw new IllegalArgumentException ("Veuillez saisir une date valide");
        }
        setDateFabrication(TimeUtils.convertToSqlDate(dateFabrication,"eng"));
    }

    public double getPrixFabrication() {
        return prixFabrication;
    }

    public void setPrixFabrication(String prixFabrication) {
        this.prixFabrication = ValidationUtils.validatePositiveStringDouble(prixFabrication);
    }

    public String getOriginalSource() {
        return originalSource;
    }

    public void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public String getParentSource() {
        return parentSource;
    }

    public void setParentSource(String parentSource) {
        this.parentSource = parentSource;
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
}
