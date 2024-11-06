<%@ page import="mg.kodoro.models.estimation.EstimationVente" %>
<%@ page import="mg.kodoro.models.estimation.AdminEstimation" %>
<%@ page import="java.util.List" %>

<%
    // Obtenir l'objet adminEstimation depuis la requête
    Object obj = request.getAttribute("adminestimation");

    if (obj == null) { 
%>
        <h1>Aucune estimation possible</h1>
<% 
    } else {
        AdminEstimation adminEstimation = (AdminEstimation) obj;
        List<EstimationVente> estimations = adminEstimation.getEstimations();
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">Estimation des Volumes</h2>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th>ID Bloc</th>
                <th>Volume</th>
                <th>Volume Restante</th>
                <th>Estimations</th>
                <th>Détails</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (estimations != null && !estimations.isEmpty()) {
                    for (EstimationVente estimation : estimations) {
                        double volume = 0;
                        double volumeRestante = 0; // Exemple de calcul pour le volume restante
            %>
                        <tr>
                            <td><%= estimation.getBloc().getIdBloc() %></td>
                            <td><%= estimation.getBloc().getVolume() %> m³</td>
                            <td><%= volumeRestante %> m³</td>
                            <!-- Collapse trigger for Estimations -->
                            <td>
                                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#estimations-<%= estimation.getIdBloc() %>" aria-expanded="false" aria-controls="estimations-<%= estimation.getIdBloc() %>">
                                    [P1, P2, P3]
                                </button>
                                <div class="collapse" id="estimations-<%= estimation.getIdBloc() %>">
                                    <div class="card card-body">
                                        <strong>P1:</strong> <%= estimation.getEstimationVente() %> m³<br>
                                        <strong>P2:</strong> <%= estimation.getEstimationResteRapportVolumePrix() %> m³<br>
                                        <strong>P3:</strong> <%= estimation.getEstimationResteVolumeMinimal() %> m³
                                    </div>
                                </div>
                            </td>
                            <!-- Collapse trigger for Details -->
                            <td>
                                <!-- <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#details-<%= estimation.getIdBloc() %>" aria-expanded="false" aria-controls="details-<%= estimation.getIdBloc() %>">
                                    [Détails Restes, Détails Fabrication]
                                </button>
                                <div class="collapse" id="details-<%= estimation.getIdBloc() %>">
                                    <div class="card card-body">
                                        <strong>Détails Restes:</strong><br>
                                            Aucune information sur les blocs restants.
                                        <strong>Détails Fabrication:</strong><br>
                                            Aucune information de transformation disponible.
                                      
                                    </div>
                                </div> -->
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5" class="text-center">Aucune estimation trouvée</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<!-- JavaScript pour activer le collapse (nécessite jQuery et Bootstrap JS) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<%    
    } 
%>
