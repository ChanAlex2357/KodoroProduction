<%@ page import="mg.kodoro.models.Bloc" %>
<%@ page import="mg.kodoro.models.DimensionUsuels" %>

<%
    // Récupérer la liste des blocs et des dimensions usuelles
    Bloc[] blocList = Bloc.getAllBlocs();
    DimensionUsuels[] dimensionUsuelsList = DimensionUsuels.getAllDimensionsUsuelles();
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Transformation</h2>
    <form action="transformation" method="post" class="needs-validation" novalidate>
        
        <!-- Sélection du bloc -->
        <div class="form-group">
            <label for="blocSelect" >Sélectionnez le Bloc</label>
            <select class="form-control" id="blocSelect" name="idBloc" required>
                <option value="" disabled selected>Choisissez un bloc</option>
                <%
                    if (blocList != null && blocList.length > 0) {
                        for (Bloc bloc : blocList) {
                %>
                            <option value="<%= bloc.getIdBloc() %>"><%= bloc.getIdBloc() %> - <%= bloc.getDesce() %></option>
                <%
                        }
                    } else {
                %>
                        <option value="" disabled>Aucun bloc trouvé</option>
                <%
                    }
                %>
            </select>
            <div class="invalid-feedback">Veuillez sélectionner un bloc.</div>
        </div>
        
        <!-- Champ pour marge en pourcentage -->
        <div class="form-group">
            <label for="margePourcentage" >Marge en Pourcentage</label>
            <input type="number" name="margePourcentage" id="margePourcentage" class="form-control" min="0" max="100" step="0.01" required>
            <div class="invalid-feedback">Veuillez entrer une marge valide (entre 0 et 100).</div>
        </div>
        <!-- Champ pour date de transformation -->
        <div class="form-group">
            <label for="dateTransformation" >Date de transformation</label>
            <input type="date" name="dateTransformation" id="dateTransformation" class="form-control" required>
        </div>
        <hr>
        <h4 class="mt-4" style="color: #FFC107;">Dimensions Usuelles</h4>
        <table class="table table-bordered mt-3">
            <thead class="thead-light">
                <tr>
                    <th>Produit</th>
                    <th>Quantité</th>
                    <th>Prix de revient Unitaire</th>
                    <th>Prix de revient</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (dimensionUsuelsList != null && dimensionUsuelsList.length > 0) {
                        for (DimensionUsuels dimension : dimensionUsuelsList) {
                %>
                            <tr>
                                <td>
                                    <input type="hidden" name="idDimensionUsuel[]" value="<%= dimension.getIdDimensionUsuels() %>">
                                    <%= dimension.getDesce() %>( 
                                        <%= dimension.getLongueur() %> x <%= dimension.getLargeur() %> x <%= dimension.getEpaisseur() %> )
                                    </td>
                                    <td>
                                        <input type="number" name="quantite[]" class="form-control" min="0" step="1" value="0" required>
                                        <div class="invalid-feedback">Entrez une quantité valide pour ce produit.</div>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" name="prixRevientunitaire[]"" min="0" step="0.1" value="0" required>
                                        <div class="invalid-feedback">Entrez un prix valide.</div>
                                    </td>
                                    <td>
                                        <input type="number" name="prixRevient[]" class="form-control" min="0" step="0.1" value="0" required>
                                        <div class="invalid-feedback">Entrez un prix valide.</div>
                                    </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="2" class="text-center">Aucune dimension usuelle trouvée</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <hr>
        <div class="form-group">
            <!-- Tableau pour ajouter des blocs restants manuellement -->
            <h4 class="mt-4" style="color: #FFC107;">Ajouter des Blocs Restants</h4>
            <table class="table table-bordered mt-3" id="blocsRestantsTable">
                <thead class="thead-light">
                    <tr>
                        <th>Longueur (m)</th>
                        <th>Largeur (m)</th>
                        <th>Épaisseur (m)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Lignes dynamiques ajoutées via JavaScript -->
                </tbody>
            </table>
            <button type="button" class="btn btn-secondary d-block" onclick="ajouterBlocRestant()">Ajouter un bloc restant</button>
        </div>
        <hr>
        <div>
            <!-- Bouton de soumission -->
            <button type="submit" class="btn btn-block mt-4" style="background-color: #FFC107; color: #FFFFFF;">Insérer la Transformation</button>
        </div>
    </form>
</div>

<!-- Script JavaScript pour ajouter des lignes dynamiques pour les blocs restants -->
<script>
    function ajouterBlocRestant() {
        // Sélectionner le tableau
        const table = document.getElementById("blocsRestantsTable").getElementsByTagName("tbody")[0];

        // Créer une nouvelle ligne
        const newRow = table.insertRow();

        // Ajouter des cellules pour longueur, largeur, épaisseur, prix de fabrication, et une action pour supprimer la ligne
        newRow.innerHTML = `
            <td><input type="number" name="longueurBlocRestant[]" step="0.01" class="form-control" value="0" required></td>
            <td><input type="number" name="largeurBlocRestant[]" step="0.01" class="form-control" value="0" required></td>
            <td><input type="number" name="epaisseurBlocRestant[]" step="0.01" class="form-control" value="0" required></td>
            <td><button type="button" class="btn btn-danger btn-sm" onclick="supprimerLigne(this)">Supprimer</button></td>
        `;
    }
    function supprimerLigne(button) {
        // Supprimer la ligne de la table
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }
    ajouterBlocRestant();
</script>
