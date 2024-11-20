<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.annexe.Unite" %>
<%
    // Récupération de la liste des unités disponibles depuis le contrôleur
    Unite[] uniteList = (Unite[]) request.getAttribute("unites");
%>

<section class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-6 bg-light p-4 rounded">
            <!-- Titre -->
            <h2 class="text-center mb-4" style="color: #FFC107;">Gestion des Unités</h2>

            <!-- Formulaire d'ajout -->
            <form action="unite" method="post" class="needs-validation" novalidate>
                <!-- Nom de l'unité -->
                <div class="form-group">
                    <label for="val">Nom de l'Unité</label>
                    <input type="text" class="form-control" id="val" name="val" placeholder="Nom de l'unité" required>
                    <div class="invalid-feedback">Veuillez entrer le nom de l'unité.</div>
                </div>

                <!-- Description de l'unité -->
                <div class="form-group mt-3">
                    <label for="desce">Description</label>
                    <input type="text" class="form-control" id="desce" name="desce" placeholder="Description de l'unité" required>
                    <div class="invalid-feedback">Veuillez entrer une description.</div>
                </div>

                <!-- Bouton de soumission -->
                <button type="submit" class="btn btn-block mt-3" style="background-color: #FFC107; color: #FFFFFF;">
                    Ajouter l'Unité
                </button>
            </form>
        </div>
    </div>
</section>
