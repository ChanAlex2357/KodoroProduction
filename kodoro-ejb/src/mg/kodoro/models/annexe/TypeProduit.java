package mg.kodoro.models.annexe;

import mg.kodoro.bean.MaClassMAPTable;
import java.sql.Connection;

public class TypeProduit extends MaClassMAPTable {
    private String idTypeProduit;
    private String val;
    private String desce;

    public TypeProduit() {
        setNomTable("TypeProduit");
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        preparePk("TPP", "GET_TYPE_PRODUIT_SEQ");
        setIdTypeProduit(makePK(c));
    }
    public TypeProduit(String idTypeProduit, String val, String desce) {
        this.idTypeProduit = idTypeProduit;
        this.val = val;
        this.desce = desce;
    }

    public String getIdTypeProduit() {
        return idTypeProduit;
    }

    public void setIdTypeProduit(String idTypeProduit) {
        this.idTypeProduit = idTypeProduit;
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
        return "idTypeProduit";
    }

    @Override
    public String getTuppleID() {
        return this.getIdTypeProduit();
    }

    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        setNomTable("TypeProduit");
        return super.createObject(c);
    }
}
