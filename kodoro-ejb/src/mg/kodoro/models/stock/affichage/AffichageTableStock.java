package mg.kodoro.models.stock.affichage;

import mg.kodoro.affichage.HtmlTableObject;
import mg.kodoro.models.stock.*;

public class AffichageTableStock extends HtmlTableObject {
    boolean originalView = false;
    EtatStockDimension[] etatStockDimensions;

    public AffichageTableStock(EtatStockDimension[] etatStockDimensions) {
        setEtatStockDimensions(etatStockDimensions);
    }
    public void enableOriginalView(){
        this.originalView = true;
    }
    public void disableOriginalView(){
        this.originalView= false;
    }
    @Override
    public String getThead() {
        String thead =
                "<thead class=\"thead-dark\">\r\n" + //
                "   <tr>\r\n";
        if (originalView) {
            thead += "       <th scope=\"col\">Id Original Source</th>\r\n";
        }
        thead +="       <th scope=\"col\">Id Dimension Usuels</th>\r\n" + //
                "       <th scope=\"col\">Quantité</th>\r\n" + //
                "       <th scope=\"col\">Entrée</th>\r\n" + //
                "       <th scope=\"col\">Sortie</th>\r\n" + //
                "       <th scope=\"col\">Prix de Revient</th>\r\n" + //
                "       <th scope=\"col\">Prix de Vente</th>\r\n" + //
                "       <th scope=\"col\">Prix de Revient Moyenne</th>\r\n" + //
                "   </tr>\r\n" + //
                "</thead>\r\n";
        return thead;
    }
    @Override
    public String getTbody() {
        String tbody ="<tbody>\r\n";

            EtatStockDimension[] etatStockDimensions = this.getEtatStockDimensions();
            if (etatStockDimensions != null && etatStockDimensions.length > 0) {
                for (EtatStockDimension etatStock : etatStockDimensions) {
                    tbody += "<tr>\r\n";
                    if (originalView) {
                        tbody += "<td><"+ etatStock.getIdOriginalSource()+"</td>\r\n";
                    }
                    tbody += "<td><"+ etatStock.getIdDimensionUsuels()+"</td>\r\n" + //
                        "<td><"+ etatStock.getQuantite()+"</td>\r\n" + //
                        "<td><"+ etatStock.getEntree()+"</td>\r\n" + //
                        "<td><"+ etatStock.getSortie()+"</td>\r\n" + //
                        "<td><"+ etatStock.getPrixDeRevient()+"</td>\r\n" + //
                        "<td><"+ etatStock.getPrixDeVente()+"</td>\r\n" + //
                        "<td><"+ etatStock.getPrixDeRevientMoyenne()+"</td>\r\n" + //
                    "</tr>\r\n";
                }   
            }
            else {
                tbody += " <tr>\r\n" + //
                        "     <td colspan=\"8\" class=\"text-center\">Aucun état de stock trouvé</td>\r\n" + //
                        " </tr>\r\n";
            }
            return tbody;
    }

    @Override
    public String getTfooter() {
        return "";
    }
    public boolean isOriginalView() {
        return originalView;
    }
    public void setOriginalView(boolean originalView) {
        this.originalView = originalView;
    }
    public EtatStockDimension[] getEtatStockDimensions() {
        return etatStockDimensions;
    }
    public void setEtatStockDimensions(EtatStockDimension[] etatStockDimensions) {
        if (etatStockDimensions == null || etatStockDimensions.length < 0) {
            disableOriginalView();
        }
        this.etatStockDimensions = etatStockDimensions;
    }
   
    
}
