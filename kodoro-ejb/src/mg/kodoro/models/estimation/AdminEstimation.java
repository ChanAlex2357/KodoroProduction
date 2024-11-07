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
    public static AdminEstimation getEstimations(Connection conn) throws Exception{
        Bloc[] blocs = Bloc.getAllBlocs();
        AdminEstimation adminEstimation =  new AdminEstimation();
        for (Bloc bloc : blocs) {
            EstimationVente estimationVente =  new EstimationVente(bloc,conn);
            estimationVente.getEstimationVente(conn);
            adminEstimation.getEstimations().add(estimationVente);

        }
        return adminEstimation;
    }
    
}