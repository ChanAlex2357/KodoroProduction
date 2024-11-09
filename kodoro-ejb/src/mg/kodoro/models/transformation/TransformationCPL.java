package mg.kodoro.models.transformation;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mg.kodoro.models.Bloc;
import mg.kodoro.utils.ValidationUtils;
import utils.TimeUtils;

public class TransformationCPL {
    private String                  idBloc;
    private double                  marge;
    private Date                    dateTransformation;
    private TransformationFille[]   detailsTransformations;
    private Bloc[]                  restes;
    private Bloc                    bloc;
    
    public TransformationCPL (
        String idBloc ,
        String margePourcentage ,
        String dateTrans ,
        String[] idDimensions ,
        String[] prixVente,
        String[] qunatite , 
        String[] prixRevient , 
        String[] longueurs , 
        String[] largeurs , 
        String[] epaisseurs
    ) throws ParseException
    {
        setIdBloc(idBloc);
        setMarge(margePourcentage);
        setDateTransformation(dateTrans);
        setTransformationFille(idDimensions,prixVente, qunatite , prixRevient );
        setRestes(longueurs, largeurs, epaisseurs , dateTrans);
    }

    public void validerTransformation(Connection conn) throws Exception{
        controllerMarge(conn);
        Transformation trans = this.genereTransformation(conn);
        this.genererBlocRestante(trans,conn);
    }
    
    public void setTransformationFille(String[] idDimensions, String[] prixVente , String[] quantite , String[] prixRevient){
        List<TransformationFille> transF = new ArrayList<>();
        for (int i = 0; i < idDimensions.length; i++) {
            try {
                transF.add(new TransformationFille(idDimensions[i],prixVente[i],quantite[i],prixRevient[i]));
            } catch ( IllegalArgumentException e) {
                System.out.println("Inutile d'inserer une valeur de 0 .NEXT!");
            }
        }
        this.setDetailsTransformations(transF.toArray(new TransformationFille[0]));
    }

    public void setRestes(String[] longueurs , String[] lageurs , String[] epaisseurs , String daty) throws ParseException{
        Bloc[] restes = new Bloc[longueurs.length];
        for (int i = 0; i < restes.length; i++) {
            restes[i] = new Bloc(longueurs[i],lageurs[i],epaisseurs[i],daty,"1");
        }
        this.setRestes(restes);
    }

    public Bloc[] genererBlocRestante(Transformation trans, Connection conn) throws Exception{
        Bloc b = this.getBloc(conn);
        if (!b.getIdBloc().equals( this.getIdBloc())) {
            throw new Exception("L'id bloc et le bloc sauvegarder ne correspondent pas ");
        }
        for (Bloc bloc : this.getRestes()) {
            bloc.setIdParentSource(this.getIdBloc());
            bloc.setIdOriginalSource(b);
            bloc.estimatePrixFabrication(b, conn); // estimer le prix de fabrication
            bloc.construirePK(conn);
            bloc.setDesce("Bloc "+bloc.getIdBloc()+" reste de transformation "+trans.getIdTransformation()+" Bloc "+trans.getIdBloc());
            bloc.createObject(conn, conn);
        }
        return restes;
    }
    public Transformation genereTransformation(Connection conn) throws Exception{
        Transformation trans = new Transformation(this.getIdBloc(), this.getMarge(), this.getDateTransformation());
        trans.createObject(conn, conn);
        TransformationLib transformationLib = trans.getAsTransformationLib(conn);
        for (TransformationFille transformationFille : detailsTransformations) {

            transformationFille.transformation = transformationLib;
            transformationFille.setIdTransformation(trans.getIdTransformation());
            transformationFille.createObject(conn);
        }
        return trans;
    }
    
    public void controllerMarge(Connection conn) throws Exception{
        double margeCalculer = getMargeVolume(conn);
        if (margeCalculer > this.getMarge()) {
            throw new Exception("La marge de volume ("+margeCalculer+" %) ne respecte pas la marge etablie ("+this.getMarge()+" %)");
        }
    }

    public double getMargeVolume(Connection conn) throws Exception{
        double vre = this.getVolumeRestanteEstime(conn);
        System.out.println("VRE : "+vre);
        double vrt = this.getVolumeRestanteTheorique();
        System.out.println("VRT : "+vrt);
        if ( vrt > vre || vrt > getBloc(conn).getVolume()) {
            throw new IllegalArgumentException("Le volume du reste ne doit pas etre inferieur");
        }
        return calculerMargeVolume(vre, vrt);
    }
    
    public double calculerMargeVolume(double volumeEstime , double volumeTheorique) {
        double reste = Math.abs(volumeTheorique - volumeEstime);
        double calc =  reste / volumeTheorique;
        double marge = calc * 100;

        System.out.println("RESTE : "+reste);
        System.out.println("CALC : "+calc);
        System.out.println("MARGE : "+marge);

        return  marge;
    }
    
    public double getVolumeRestanteEstime(Connection conn) throws Exception{
        double vb = getBloc(conn).getVolume(); // Volume du bloc
        double vtt = this.getVolumeTotalTransformer(conn); // Volume total transformer
        
        System.out.println("VB :"+vb);
        System.out.println("VTT : "+vtt);
        return vb - vtt;
    }
    
    public double getVolumeTotalTransformer(Connection conn) throws Exception{
        return TransformationFille.getSommeVolume(this.getDetailsTransformations(), conn);
    }
    public double getVolumeRestanteTheorique(){
        double vtt = Bloc.getSommeVolume(this.getRestes());
        return vtt;

    }
    
    public void estimateFabricationBlocRestantes(Connection conn) throws Exception{
        // Recuperer prix unitaire de volume
        Bloc bloc = getBloc(conn);
        for (Bloc blc : getRestes()) {
            blc.estimatePrixFabrication(bloc, conn);
        }
    }
    
    public Bloc getBloc(Connection conn){
        if (this.bloc != null ) {
            System.out.println("BLOC EXIST : "+this.bloc.getIdBloc());
            return bloc;
        }
        try {
            this.bloc = Bloc.getById(idBloc, conn);
            System.out.println("INIT BLOC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.bloc;
    }
    // Getteurs et Setteurs
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
    public void setMarge(String marge){
        setMarge( ValidationUtils.validatePositiveStringDouble(marge) );
    }
    public TransformationFille[] getDetailsTransformations() {
        return detailsTransformations;
    }
    public void setDetailsTransformations(TransformationFille[] detailsTransformations) {
        this.detailsTransformations = detailsTransformations;
    }
    public Bloc[] getRestes() {
        return restes;
    }
    public void setRestes(Bloc[] restes) {
        this.restes = restes;
    }
    public Date getDateTransformation() {
        return dateTransformation;
    }
    
    public void setDateTransformation(Date dateTransformation) {
        this.dateTransformation = dateTransformation;
    }

    public void setDateTransformation(String dateTransformation) throws ParseException{
        setDateTransformation( TimeUtils.convertToSqlDate(dateTransformation, "eng"));
    }
}
