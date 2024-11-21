<%@ page import = "mg.kodoro.models.annexe.Produit"%>

<%
    Produit[] produits = (Produit[]) request.getAttribute("produits");
%>


<div class="container mt-5">
    <h2 class="text-center" style="color: #4CAF50;">Formulaire d'Insertion d'Achat</h2>
    <form action="achat" method="post" class="mt-4">
        <div class="form-group">
            <label for="quantite">Quantité:</label>
            <input type="number" step="0.01" class="form-control" id="quantite" name="quantite" placeholder="Entrez la quantité" required>
        </div>
        <div class="form-group">
            <label for="puAchat">Prix Unitaire d'Achat:</label>
            <input type="number" step="0.01" class="form-control" id="puAchat" name="puAchat" placeholder="Entrez le prix unitaire" required>
        </div>
        <div class="form-group">
            <label for="dateAchat">Date d'Achat:</label>
            <input type="date" class="form-control" id="dateAchat" name="dateAchat" required>
        </div>
        <div class="form-group">
            <label for="idProduit">Produit:</label>
            <select class="form-control" id="idProduit" name="idProduit" required>
                <%
                    if (produits != null && produits.length > 0) {
                        for (Produit produit : produits) {
                %>
                            <option value="<%= produit.getIdProduit() %>"><%= produit.getDesce() %></option>
                <%
                        }
                    } else {
                %>
                        <option value="" disabled>Aucune produit disponible</option>
                <%
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter l'Achat</button>
    </form>
</div>