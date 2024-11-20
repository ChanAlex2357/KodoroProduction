package mg.kodoro.models.production;

import java.sql.Connection;

public class AdminFormuleProduction {
    String desceFormule;
    String[] idRessources;
    String[] quantites;

    public AdminFormuleProduction(String desce , String[] ressources , String[] quantites) {
        setDesceFormule(desce);
        setIdRessources(ressources);
        setQuantites(quantites);
    }
    public FormuleProduction genererFormule(Connection conn) throws Exception{
        FormuleProductionFille[] detailsFormule = new FormuleProductionFille[getIdRessources().length];
        for (int i = 0; i < idRessources.length; i++) {
            detailsFormule[i] = new FormuleProductionFille(getIdRessources()[i],getQuantites()[i]); 
        }
        FormuleProduction formuleProduction = new FormuleProduction();
        formuleProduction.setDesce(this.getDesceFormule());
        formuleProduction.setDetailsFormule(detailsFormule);
        formuleProduction.createObject(conn);
        return formuleProduction;
    }
    public String getDesceFormule() {
        return desceFormule;
    }

    public void setDesceFormule(String desceFormule) {
        this.desceFormule = desceFormule;
    }

    public String[] getIdRessources() {
        return idRessources;
    }

    public void setIdRessources(String[] idRessources) {
        this.idRessources = idRessources;
    }

    public String[] getQuantites() {
        return quantites;
    }

    public void setQuantites(String[] quantites2) {
        this.quantites = quantites2;
    }
}
