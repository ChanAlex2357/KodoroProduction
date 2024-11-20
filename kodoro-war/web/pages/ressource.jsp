<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.annexe.Unite" %>
<%
    // Récupération de la liste des unités disponibles (par exemple depuis le contrôleur)
    Unite[] uniteList = (Unite[]) request.getAttribute("uniteList");
%>

<section class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-6 bg-light p-4 rounded">
            <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Nouvelle Ressource</h2>
            <form action="ressource" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="desce">Description</label>
                    <input type="text" class="form-control" id="desce" name="desce" placeholder="Description de la ressource" required>
                    <div class="invalid-feedback">Veuillez entrer une description.</div>
                </div>

                <div class="form-group">
                    <label for="puAchat">Prix Unitaire d'Achat (Ar)</label>
                    <input type="number" step="0.01" class="form-control" id="puAchat" name="puAchat" placeholder="Prix unitaire d'achat" required>
                    <div class="invalid-feedback">Veuillez entrer le prix unitaire d'achat.</div>
                </div>

                <div class="form-group">
                    <label for="idUnite">Unité</label>
                    <select class="form-select" id="idUnite" name="idUnite" required>
                        <option value="" disabled selected>Choisissez une unité</option>
                        <% 
                            if (uniteList != null && uniteList.length > 0) {
                                for (Unite unite : uniteList) {
                        %>
                                    <option value="<%= unite.getIdUnite() %>"><%= unite.getDesce() %></option>
                        <% 
                                }
                            } else { 
                        %>
                                <option value="" disabled>Aucune unité disponible</option>
                        <% 
                            } 
                        %>
                    </select>
                    <div class="invalid-feedback">Veuillez sélectionner une unité.</div>
                </div>

                <button type="submit" class="btn btn-block mt-3" style="background-color: #FFC107; color: #FFFFFF;">Ajouter la Ressource</button>
            </form>
        </div>
    </div>
</section>
