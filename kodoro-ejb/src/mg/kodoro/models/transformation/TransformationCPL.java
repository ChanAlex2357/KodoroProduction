package mg.kodoro.models.transformation;

import java.sql.Connection;
import java.sql.Date;

import mg.kodoro.models.Bloc;
import mg.kodoro.utils.ValidationUtils;

public class TransformationCPL {
    private String                  idBloc;
    private double                  marge;
    private Date                    dateTransformation;
    private TransformationFille[]   detailsTransformations;
    private Bloc[]                  restes;
    private Bloc                    bloc;
    
    public void validerTransformation(Connection conn) throws Exception{
        controllerMarge(conn);
        this.genereTransformation(conn);
    }
    
    public Bloc[] genererBlocRestante(Connection conn) throws Exception{
        Bloc b = this.getBloc(conn);
        if (!b.getIdBloc().equals( this.getIdBloc())) {
            throw new Exception("L'id bloc et le bloc sauvegarder ne correspondent pas ");
        }
        for (Bloc bloc : this.getRestes()) {
            bloc.estimatePrixFabrication(b, conn); // estimer le prix de fabrication
            bloc.setIdParentSource(this.getIdBloc());
            bloc.setIdOriginalSource(bloc);
            bloc.createObject(conn, conn);

        }
        return restes;
    }
    public Transformation genereTransformation(Connection conn) throws Exception{
        Transformation trans = new Transformation(this.getIdBloc(), this.getMarge(), this.getDateTransformation());
        trans.createObject(conn, conn);
        for (TransformationFille transformationFille : detailsTransformations) {
            transformationFille.setIdTransformation(trans.getIdTransformation());
            transformationFille.createObject(conn, conn);
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
        double vrt = this.getVolumeRestanteTheorique();
        return calculerMargeVolume(vre, vrt);
    }
    
    public double calculerMargeVolume(double volumeEstime , double volumeTheorique) {
        return (Math.abs(volumeTheorique - volumeEstime) / volumeTheorique) * 100;
    }
    
    public double getVolumeRestanteEstime(Connection conn) throws Exception{
        double vb = getBloc(conn).getVolume(); // Volume du bloc
        double vtt = this.getVolumeTotalTransformer(conn); // Volume total transformer   
        return vb - vtt;
    }
    
    public double getVolumeTotalTransformer(Connection conn) throws Exception{
        return TransformationFille.getSommeVolume(this.getDetailsTransformations(), conn);
    }
    public double getVolumeRestanteTheorique(){
        return Bloc.getSommeVolume(this.getRestes());
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
            return bloc;
        }
        try {
            this.bloc = Bloc.getById(idBloc, conn);
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

    public void setDateTransformation(String dateTransformation){

    }
}
