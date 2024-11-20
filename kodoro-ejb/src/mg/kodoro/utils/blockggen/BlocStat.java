package mg.kodoro.utils.blockggen;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.ViewClassMAPTable;

public class BlocStat extends ViewClassMAPTable{
    private double prixDeRevientMoyenne;
    public BlocStat(){
        setNomTable("v_prblocoriginel");
    }
    public static BlocStat getBlocStat(Connection conn) throws Exception{
        BlocStat blocStat = (BlocStat) CGenUtil.rechercher(new BlocStat(), null , null , conn ,"")[0];
        return blocStat;
    }
    public double getPrixDeRevientMoyenne() {
        return prixDeRevientMoyenne;
    }

    public void setPrixDeRevientMoyenne(double prixDeRevientMoyenne) {
        this.prixDeRevientMoyenne = prixDeRevientMoyenne;
    }
}
