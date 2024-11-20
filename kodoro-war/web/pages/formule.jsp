<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.annexe.Ressource, mg.kodoro.models.production.FormuleProduction, mg.kodoro.models.production.FormuleProductionFille" %>
<%
    // Liste des ressources disponibles (à récupérer depuis le contrôleur)
    Ressource[] ressourceList = (Ressource[]) request.getAttribute("ressourceList");
%>

<section class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-8 bg-light p-4 rounded">
            <h2 class="text-center mb-4" style="color: #FFC107;">Création d'une Formule de Production</h2>
            <form action="formule" method="post" class="needs-validation" novalidate>
                
                <!-- Description de la formule -->
                <div class="form-group">
                    <label for="desce">Description</label>
                    <input type="text" class="form-control" id="desce" name="desce" placeholder="Description de la formule" required>
                    <div class="invalid-feedback">Veuillez entrer une description.</div>
                </div>

                <!-- Tableau des détails de formule -->
                <div class="form-group mt-4">
                    <label>Détails de la Formule</label>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Ressource</th>
                                <th>Quantité</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="formuleDetailsBody">
                            <tr>
                                <td>
                                    <select class="form-select" name="idRessource[]" required>
                                        <option value="" disabled selected>Choisissez une ressource</option>
                                        <% 
                                            if (ressourceList != null && ressourceList.length > 0) {
                                                for (Ressource res : ressourceList) {
                                        %>
                                                    <option value="<%= res.getIdRessource() %>"><%= res.getDesce() %></option>
                                        <% 
                                                }
                                            } else { 
                                        %>
                                                <option value="" disabled>Aucune ressource disponible</option>
                                        <% 
                                            } 
                                        %>
                                    </select>
                                    <div class="invalid-feedback">Veuillez sélectionner une ressource.</div>
                                </td>
                                <td>
                                    <input type="number" step="0.01" class="form-control" name="quantite[]" placeholder="Quantité" required>
                                    <div class="invalid-feedback">Veuillez entrer une quantité valide.</div>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">Supprimer</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <button type="button" class="btn btn-success btn-sm" onclick="addRow()">Ajouter un détail</button>
                </div>

                <!-- Bouton de soumission -->
                <button type="submit" class="btn btn-block mt-3" style="background-color: #FFC107; color: #FFFFFF;">Créer la Formule</button>
            </form>
        </div>
    </div>
</section>

<script>
    // Fonction pour ajouter une nouvelle ligne au tableau
    function addRow() {
        const tableBody = document.getElementById('formuleDetailsBody');
        const newRow = document.createElement('tr');

        newRow.innerHTML = `
            <td>
                <select class="form-select" name="idRessource[]" required>
                    <option value="" disabled selected>Choisissez une ressource</option>
                    <% 
                        if (ressourceList != null && ressourceList.length > 0) {
                            for (Ressource res : ressourceList) {
                    %>
                                <option value="<%= res.getIdRessource() %>"><%= res.getDesce() %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
                <div class="invalid-feedback">Veuillez sélectionner une ressource.</div>
            </td>
            <td>
                <input type="number" step="0.01" class="form-control" name="quantite[]" placeholder="Quantité" required>
                <div class="invalid-feedback">Veuillez entrer une quantité valide.</div>
            </td>
            <td>
                <button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">Supprimer</button>
            </td>
        `;

        tableBody.appendChild(newRow);
    }

    // Fonction pour supprimer une ligne du tableau
    function removeRow(button) {
        const row = button.closest('tr');
        row.remove();
    }
</script>
