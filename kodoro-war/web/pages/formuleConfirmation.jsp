<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.production.FormuleProduction, mg.kodoro.models.production.FormuleProductionFille" %>

<%
    FormuleProduction formule = (FormuleProduction) request.getAttribute("formule");
    String message = (String) request.getAttribute("message");
%>

<section class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-8 bg-light p-4 rounded">
            <h2 class="text-center mb-4" style="color: #FFC107;">Confirmation</h2>
            <p><%= message %></p>
            <p><strong>Description :</strong> <%= formule.getDesce() %></p>
            <h3>Détails :</h3>
            <ul>
                <% for (FormuleProductionFille detail : formule.getDetails()) { %>
                    <li>Ressource ID : <%= detail.getIdRessource() %>, Quantité : <%= detail.getQuantite() %></li>
                <% } %>
            </ul>
            <a href="formule" class="btn btn-primary">Créer une autre formule</a>
        </div>
    </div>
</section>
