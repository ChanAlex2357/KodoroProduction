package mg.kodoro.models.stock;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class EtatStockDimension extends MaClassMAPTable{;
    protected String idEtatStockDimension;
    protected String idOriginalSource;
    protected String idDimensionUsuels;
    protected double quantite;
    protected double valeurTotal;
    protected double valeurUnitaire;
    public EtatStockDimension(){setNomTable("EtatStockDimension");}
    
    @Override
    public MaClassMAPTable createObject(Connection c) throws Exception {
        controllerValeur();
                return super.createObject(c);
    }
    
    private void controllerValeur() {
        if (valeurTotal == 0 && valeurUnitaire > 0) {
            this.setValeurTotal( this.getValeurUnitaire() * this.getQuantite());
        }
    }

    public void updateEtatStock(MvtStockDimension mvt , Connection c) throws Exception {
        
    }
        
    @Override
    public MaClassMAPTable createObject(Connection localconn, Connection remoteconn) throws Exception {
        return this.createObject(localconn);
    }
    @Override
    public String getAttributIDName() {
        return "idEtatStockDimension";
    }
    @Override
    public String getTuppleID() {
        return this.getIdEtatStockDimension();
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("STD", "GET_STD_SEQ");
        this.setIdEtatStockDimension(this.makePK(c));
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public String getIdOriginalSource() {
        return idOriginalSource;
    }
    public void setIdOriginalSource(String idOriginalSource) {
        this.idOriginalSource = idOriginalSource;
    }
    public String getIdDimensionUsuels() {
        return idDimensionUsuels;
    }
    public void setIdDimensionUsuels(String idDimensionUsuels) {
        this.idDimensionUsuels = idDimensionUsuels;
    }
        
    public static EtatStockDimension[] getEtatDeStocks(Connection conn) throws Exception {
        EtatStockDimension[] etatStocks = (EtatStockDimension[]) CGenUtil.rechercher(new EtatStockDimension(),null,null,conn,"");
        if (etatStocks.length >  0) {
            return etatStocks;
        }
        return null;
    }
    public String getIdEtatStockDimension() {
        return idEtatStockDimension;
    }
    public void setIdEtatStockDimension(String idEtatStockDimension) {
        this.idEtatStockDimension = idEtatStockDimension;
    }
    public double getValeurTotal() {
        return valeurTotal;
    }
    public void setValeurTotal(double valeurTotal) {
        this.valeurTotal = valeurTotal;
    }
    public double getValeurUnitaire() {
        return valeurUnitaire;
    }
    public void setValeurUnitaire(double valeurUnitaire) {
        this.valeurUnitaire = valeurUnitaire;
    }
}
