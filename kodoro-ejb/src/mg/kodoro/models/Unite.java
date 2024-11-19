package mg.kodoro.models;

import java.sql.Connection;

import mg.kodoro.bean.MaClassMAPTable;

public class Unite extends MaClassMAPTable{
    protected String idUnite;
    protected String val;
    protected String desce;

    public Unite(){
        setNomTable("Unite");
    }
    public Unite(String val , String desce) {
        setVal(val);
        setDesce(desce);
    }
    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("Unite");
        return super.createObject(c);
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("UNI", "GET_UNITE_SEQ");
        setIdUnite(makePK(c));
    }
    @Override
    public String getAttributIDName() {
        return "idUnite";
    }
    @Override
    public String getTuppleID() {
        return this.getIdUnite();
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) {
        this.idUnite = idUnite;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesce() {
        return desce;
    }

    public void setDesce(String desce) {
        this.desce = desce;
    }

    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createObject'");
    }
}
