package mg.kodoro.utils.blockggen;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class BlocStat extends MaClassMAPTable{
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

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObject'");
    }
    @Override
    public String getAttributIDName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributIDName'");
    }
    @Override
    public String getTuppleID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTuppleID'");
    }
}
