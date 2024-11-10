<%@ page import="mg.kodoro.models.stock.EtatStockDimension" %>
<%@ page import="mg.kodoro.models.stock.AdminEtatStockDimension" %>

<%
    Object obj = request.getAttribute("adminEtatStockDimension");
    if(obj == null){ %>
        <h1>Aucnune Stock Disponible</h1>
<%    }
    else
    {
%>

<%
    AdminEtatStockDimension adminEtat = (AdminEtatStockDimension) obj;
    EtatStockDimension[] etatStockList = adminEtat.getEtatStockDimensions();
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">État de Stock des Dimensions</h2>
    
    <table class="table table-striped mt-3">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Id Original Source</th>
                <th scope="col">Id Dimension Usuels</th>
                <th scope="col">Quantité</th>
                <th scope="col">Entrée</th>
                <th scope="col">Sortie</th>
                <th scope="col">Prix de Revient</th>
                <th scope="col">Prix de Vente</th>
                <th scope="col">Prix de Revient Moyenne</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (etatStockList != null && etatStockList.length > 0) {
                    for (EtatStockDimension etatStock : etatStockList) {
            %>
                        <tr>
                            <td><%= etatStock.getIdOriginalSource() %></td>
                            <td><%= etatStock.getIdDimensionUsuels() %></td>
                            <td><%= etatStock.getQuantite() %></td>
                            <td><%= etatStock.getEntree() %></td>
                            <td><%= etatStock.getSortie() %></td>
                            <td><%= etatStock.getPrixDeRevient() %></td>
                            <td><%= etatStock.getPrixDeVente() %></td>
                            <td><%= etatStock.getPrixDeRevientMoyenne() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="8" class="text-center">Aucun état de stock trouvé</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
<%    }
%>
