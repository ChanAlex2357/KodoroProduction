package mg.kodoro.models.annexe;

import mg.kodoro.bean.MaClassMAPTable;
import java.sql.Connection;

public class TypeMvt extends MaClassMAPTable {
    private String idTypeMvt;
    private String val;
    private String desce;

    public TypeMvt() {
        setNomTable("TypeMvt");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("TPM", "GET_TYPE_MVT_SEQ");
        setIdTypeMvt(makePK(c));
    }
    public TypeMvt(String idTypeMvt, String val, String desce) {
        this.idTypeMvt = idTypeMvt;
        this.val = val;
        this.desce = desce;
    }

    public String getIdTypeMvt() {
        return idTypeMvt;
    }

    public void setIdTypeMvt(String idTypeMvt) {
        this.idTypeMvt = idTypeMvt;
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
    public String getAttributIDName() {
        return "idTypeMvt";
    }

    @Override
    public String getTuppleID() {
        return this.getIdTypeMvt();
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TypeMvt");
        return super.createObject(c);
    }
}
