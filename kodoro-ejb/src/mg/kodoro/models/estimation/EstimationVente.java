package mg.kodoro.models.estimation;

import java.sql.Connection;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.transformation.Transformation;
import mg.kodoro.models.transformation.TransformationFille;

public class EstimationVente {
    private Bloc[] blocRestantes;
    private Transformation[] transformation;
    private TransformationFille[] detailsTransformation;
    private double estimationVente = -1;
    private double estimationResteRapportVolumePrix = 1234;
    private double estimationResteVolumeMinimal = 5678;
    private Bloc bloc;

    public EstimationVente(Bloc bloc){
        setBloc(bloc);
    }
    public double getEstimationVente(){
        return this.estimationVente;
    }
    public double getEstimationVente(Connection conn) throws Exception {
        if (this.estimationVente < 0) {
            return this.estimationVente;
        }
        setEstimationVente(getBloc().getEstimationVente(conn));
        return estimationVente;
    }
    public double getEstimationResteRapportVolumePrix() {
        return estimationResteRapportVolumePrix;
    }
    public double getEstimationResteVolumeMinimal() {
        return estimationResteVolumeMinimal;
    }
    
    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }
    public Bloc[] getBlocRestantes() {
        return blocRestantes;
    }

    public void setBlocRestantes(Bloc[] blocRestantes) {
        this.blocRestantes = blocRestantes;
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
}

