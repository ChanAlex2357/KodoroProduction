package mg.kodoro.models.estimation;

import mg.kodoro.models.Bloc;
import mg.kodoro.models.transformation.Transformation;
import mg.kodoro.models.transformation.TransformationFille;

public class EstimationVente {
    private String idBloc;
    private Bloc[] blocRestantes;
    private Transformation[] transformation;
    private TransformationFille[] detailsTransformation;
    private double estimationVente;
    private double estimationResteRapportVolumePrix;
    private double estimationResteVolumeMinimal;
    
    
    public double getEstimationVente() {
        return estimationVente;
    }
    public double getEstimationResteRapportVolumePrix() {
        return estimationResteRapportVolumePrix;
    }
    public double getEstimationResteVolumeMinimal() {
        return estimationResteVolumeMinimal;
    }
    
    public String getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(String idBloc) {
        this.idBloc = idBloc;
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
}

