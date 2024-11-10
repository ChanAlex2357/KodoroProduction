package mg.kodoro.models.stock;

import java.sql.Connection;

import bean.CGenUtil;
import mg.kodoro.bean.MaClassMAPTable;

public class EtatStockDim extends MaClassMAPTable{
    protected String idDimensionUsuels;
    protected double quantite;
    protected double entree;
    protected double sortie;
    protected double prixDeRevient;
    protected double prixDeVente;
    protected double prixDeRevientMoyenne;
    
    public EtatStockDim(){setNomTable("EtatStockDimen");}

    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public String getIdDimensionUsuels() {
        return idDimensionUsuels;
    }
    public void setIdDimensionUsuels(String idDimensionUsuels) {
        this.idDimensionUsuels = idDimensionUsuels;
    }
        
    public static EtatStockDim[] getEtatDeStocks(Connection conn) throws Exception {
        EtatStockDim[] etatStocks = (EtatStockDim[]) CGenUtil.rechercher(new EtatStockDim(),null,null,conn," order by idDimensionUsuels");
        if (etatStocks.length >  0) {
            return etatStocks;
        }
        return null;
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
    public double getEntree() {
        return entree;
    }
    public void setEntree(double entree) {
        this.entree = entree;
    }
    public double getSortie() {
        return sortie;
    }
    public void setSortie(double sortie) {
        this.sortie = sortie;
    }
    public double getPrixDeRevient() {
        return prixDeRevient;
    }
    public void setPrixDeRevient(double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }
    public double getPrixDeVente() {
        return prixDeVente;
    }
    public void setPrixDeVente(double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }
    public double getPrixDeRevientMoyenne() {
        return prixDeRevientMoyenne;
    }
    public void setPrixDeRevientMoyenne(double prixDeRevientMoyenne) {
        this.prixDeRevientMoyenne = prixDeRevientMoyenne;
    }
}
