package mg.kodoro.models.estimation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mg.kodoro.models.Bloc;

public class AdminEstimation {
    List<EstimationVente> estimations;
    public AdminEstimation(){
        setEstimations(new ArrayList<>());
    }
    public List<EstimationVente> getEstimations() {
        return estimations;
    }
    public void setEstimations(List<EstimationVente> estimations) {
        this.estimations = estimations;
    }
    public static AdminEstimation getEstimations(Connection conn){
        Bloc[] blocs = Bloc.getAllBlocs();
        AdminEstimation adminEstimation =  new AdminEstimation();
        for (Bloc bloc : blocs) {
            adminEstimation.getEstimations().add( new EstimationVente(bloc));
        }
        return adminEstimation;
    }
    
}