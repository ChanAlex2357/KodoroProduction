<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Transformation</h2>
    <form action="insererTransformationAction.jsp" method="post" class="needs-validation" novalidate>
        
        <!-- Sélection du bloc -->
        <div class="form-group">
            <label for="blocSelect" style="color: #FFC107;">Sélectionnez le Bloc</label>
            <select class="form-control" id="blocSelect" name="idBloc" required>
                <option value="" disabled selected>Choisissez un bloc</option>
                <!-- Boucle pour afficher les blocs -->
                <c:forEach var="bloc" items="${blocs}">
                    <option value="${bloc.idBloc}">${bloc.idBloc} - ${bloc.desce}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">Veuillez sélectionner un bloc.</div>
        </div>

        <!-- Tableau des dimensions usuelles -->
        <h4 class="mt-4" style="color: #FFC107;">Dimensions Usuelles</h4>
        <table class="table table-bordered mt-3">
            <thead class="thead-light">
                <tr>
                    <th>Produit</th>
                    <th>Quantité</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dimension" items="${dimensionsUsuelles}">
                    <tr>
                        <td>
                            <input type="hidden" name="idDimensionUsuel[]" value="${dimension.idDimensionUsuels}">
                            ${dimension.longueur} x ${dimension.largeur} x ${dimension.epaisseur} (Prix : ${dimension.prixVente} Ar)
                        </td>
                        <td>
                            <input type="number" name="quantite[]" class="form-control" min="0" step="1" required>
                            <div class="invalid-feedback">Entrez une quantité valide pour ce produit.</div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

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
        <button type="button" class="btn btn-secondary" onclick="ajouterBlocRestant()">Ajouter un bloc restant</button>

        <!-- Bouton de soumission -->
        <button type="submit" class="btn btn-block mt-4" style="background-color: #FFC107; color: #FFFFFF;">Insérer la Transformation</button>
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
            <td><input type="number" name="longueurBlocRestant[]" step="0.01" class="form-control" required></td>
            <td><input type="number" name="largeurBlocRestant[]" step="0.01" class="form-control" required></td>
            <td><input type="number" name="epaisseurBlocRestant[]" step="0.01" class="form-control" required></td>
            <td><input type="number" name="prixFabricationBlocRestant[]" step="0.01" class="form-control" required></td>
            <td><button type="button" class="btn btn-danger btn-sm" onclick="supprimerLigne(this)">Supprimer</button></td>
        `;
    }
    function supprimerLigne(button) {
        // Supprimer la ligne de la table
        const row = button.parentNode.parentNode;
        row.parentNode.removeChild(row);
    }
</script>
