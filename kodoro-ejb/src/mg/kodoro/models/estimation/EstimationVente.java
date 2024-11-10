package mg.kodoro.models.estimation;

import java.sql.Connection;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.models.transformation.Transformation;
import mg.kodoro.models.transformation.TransformationFille;

public class EstimationVente {
    private Bloc[] blocRestantes;
    private double volumeRestantes;
    private Transformation[] transformation;
    private TransformationFille[] detailsTransformation;
    private double estimationVente = -1;
    private double estimationResteRapportVolumePrix = -1;
    private double estimationResteVolumeMinimal = -1;
    private Bloc bloc;
    
    public EstimationVente(Bloc bloc , Connection conn) throws Exception{
        setBloc(bloc);
        getEstimationVente(conn);
        getEstimationResteVolumeMinimal(conn);
        getEstimationResteRapportVolumePrix(conn);
    }
    public double getEstimationVente(){
        return this.estimationVente;
    }
    public double getEstimationVente(Connection conn) throws Exception {
        if (this.estimationVente > 0) {
            return this.estimationVente;
        }
        setEstimationVente(getBloc().getEstimationVente(conn));
        return estimationVente;
    }
    public double getEstimationResteRapportVolumePrix() {
        return estimationResteRapportVolumePrix;
    }
    public double getEstimationResteRapportVolumePrix(Connection conn) throws Exception {
        if (this.getEstimationResteRapportVolumePrix() < 0 ) {
            // Recuperation des reste
            Bloc[] restes = this.getBlocRestantes(conn);
            DimensionUsuels dim = DimensionUsuels.getDimensinoUsuelsWithMaxRapportVolumePrix(conn);
            setestimationResteRapportVolumePrix( getEstimationDimensionByBlocs(restes, dim) );
        }
        return this.getEstimationResteRapportVolumePrix();
    }
    public double getEstimationResteVolumeMinimal() {
        return estimationResteVolumeMinimal;
    }
    public double getEstimationResteVolumeMinimal(Connection conn) throws Exception {
        if (this.getEstimationResteVolumeMinimal() < 0) {
            Bloc[] restes = this.getBlocRestantes(conn);
            DimensionUsuels dim = DimensionUsuels.getDimensionUsuelsWithMinimalVolume(conn);
            setestimationResteVolumeMinimal( getEstimationDimensionByBlocs(restes, dim) );
        }
        return this.getEstimationResteVolumeMinimal();
    }
    public double getEstimationDimensionByBlocs(Bloc[] blocs , DimensionUsuels dimensionUsuels){
        if (blocs == null || blocs.length <= 0 || dimensionUsuels == null) {
            return 0;
        }
        // Evaluer la quantite possible
        double qte = dimensionUsuels.getEstimationQte(blocs[0]);
        // Estimer le montant de vente
        return dimensionUsuels.getMontantVente(qte);
    }
    
    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }
    public Bloc[] getBlocRestantes(){
        return this.blocRestantes;
    }
    public Bloc[] getBlocRestantes(Connection conn) throws Exception {
        if (this.blocRestantes == null || this.blocRestantes.length <= 0) {
            this.blocRestantes = this.getBloc().getRestes(conn);
            setVolumeRestantes(Bloc.getSommeVolume(this.blocRestantes));
        }
        return blocRestantes;
    }
    
    public Transformation[] getTransformation() {
        return transformation;
    }
    
    public void setTransformation(Transformation[] transformation) {
        this.transformation = transformation;
    }
    
    public TransformationFille[] getDetailsTransformation() {
        return detailsTransformation;
    }
    
    public void setDetailsTransformation(TransformationFille[] detailsTransformation) {
        this.detailsTransformation = detailsTransformation;
    }
    
    
    public void setEstimationVente(double estimationVente) {
        this.estimationVente = estimationVente;
    }
    
    
    public void setestimationResteRapportVolumePrix(double estimationResteRapportVolumePrix) {
        this.estimationResteRapportVolumePrix = estimationResteRapportVolumePrix;
    }
    
    public void setestimationResteVolumeMinimal(double estimationResteVolumeMinimal) {
        this.estimationResteVolumeMinimal = estimationResteVolumeMinimal;
    }
    public Bloc getBloc(){
        return this.bloc;
    }
    public String getIdBloc(){
        return this.getBloc().getIdBloc();
    }
    public double getVolumeRestantes() {
        return volumeRestantes;
    }
    public void setVolumeRestantes(double volumeRestantes) {

        this.volumeRestantes = volumeRestantes;
    }
}

