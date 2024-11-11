<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.estimation.EstimationVente" %>
<%@ page import="mg.kodoro.models.estimation.AdminEstimation" %>
<%@ page import="mg.kodoro.models.Bloc" %>
<%@ page import="java.util.List" %>

<%
    Object obj = request.getAttribute("adminestimation");

    if (obj == null) { 
%>
    <h1 class="text-center mt-5" style="color: #FF6F61;">Aucune estimation possible</h1>
<% 
    } else {
        AdminEstimation adminEstimation = (AdminEstimation) obj;
        List<EstimationVente> estimations = adminEstimation.getEstimations();
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">Estimation des Volumes</h2>
    <table class="table table-hover table-bordered fade-in-table">
        <thead class="thead-dark">
            <tr>
                <th>ID Bloc</th>
                <th>Volume</th>
                <th>Volume Restante</th>
                <th>Blocs Restantes</th>
                <th>Estimation Prix</th>
                <th>Chiffre d'Affaire</th>
                <th>Prix de Revient Total</th>
                <th>Bénéfice Théorique</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (estimations != null && !estimations.isEmpty()) {
                    for (EstimationVente estimation : estimations) {
                        double volume = 0;
            %>
                        <tr class="fade-in-row">
                            <td rowspan="3"><%= estimation.getBloc().getIdBloc() %></td>
                            <td rowspan="3"><%= estimation.getBloc().getVolume() %> m³</td>
                            <td rowspan="3"><%= estimation.getVolumeRestantes() %> m³</td>
                            <td rowspan="3">
                                <ul class="list-unstyled">
                                    <%
                                    Bloc[] restes = estimation.getBlocRestantes();
                                    for( Bloc reste : restes) { %>
                                        <li class="nav-item"><%= reste.getDesceVolume() %></li>    
                                    <% } %>
                                </ul>
                            </td>
                            <td><strong>P1</strong></td>
                            <td><%= estimation.getVente().getChiffreAffaireTheorique() %></td>
                            <td><%= estimation.getVente().getPrixDeRevientTotal() %></td>
                            <td><%= estimation.getVente().getBeneficeTheorique() %></td>
                            <td rowspan="3">
                                <button class="btn btn-warning btn-sm detail-btn" data-toggle="collapse" data-target="#details<%= estimation.getIdBloc() %>">Détails</button>
                            </td>
                        </tr>
                        <tr class="fade-in-row">
                            <td><strong>P2</strong></td>
                            <td><%= estimation.getOptimal().getChiffreAffaireTheorique() %></td>
                            <td><%= estimation.getOptimal().getPrixDeRevientTotal() %></td>
                            <td><%= estimation.getOptimal().getBeneficeTheorique() %></td>
                        </tr>
                        <tr class="fade-in-row">
                            <td><strong>P3</strong></td>
                            <td><%= estimation.getMinimal().getChiffreAffaireTheorique() %></td>
                            <td><%= estimation.getMinimal().getPrixDeRevientTotal() %></td>
                            <td><%= estimation.getMinimal().getBeneficeTheorique() %></td>
                        </tr>
                        <tr id="details<%= estimation.getIdBloc() %>" class="collapse bg-light fade-in-row">
                            <td colspan="9" class="text-center">
                                <a href="etatstock?idBloc=<%= estimation.getIdBloc() %>">Voir détails du stock des dimensions</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="9" class="text-center text-muted">Aucune estimation trouvée</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<!-- JavaScript pour activer le collapse et les animations -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<style>
    .fade-in-table {
        animation: fadeIn 1s ease-in;
    }
    .fade-in-row {
        animation: fadeInRow 0.8s ease-in;
    }
    @keyframes fadeIn {
        from { opacity: 0; }
        to { opacity: 1; }
    }
    @keyframes fadeInRow {
        from { opacity: 0; transform: translateY(-10px); }
        to { opacity: 1; transform: translateY(0); }
    }
    .detail-btn {
        background-color: #FFC107;
        color: #FFFFFF;
    }
</style>

<%    
    } 
%>
