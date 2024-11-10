<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="mg.kodoro.models.estimation.EstimationVente" %>
<%@ page import="mg.kodoro.models.estimation.AdminEstimation" %>
<%@ page import="mg.kodoro.models.Bloc" %>
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
                <th>Blocs restantes</th>
                <th>Estimations</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (estimations != null && !estimations.isEmpty()) {
                    for (EstimationVente estimation : estimations) {
                        double volume = 0;
            %>
                        <tr>
                            <td><%= estimation.getBloc().getIdBloc() %></td>
                            <td><%= estimation.getBloc().getVolume() %> m³</td>
                            <td><%= estimation.getVolumeRestantes() %> m³</td>
                            <td>
                                <ul>

                                    <%
                                    Bloc[] restes = estimation.getBlocRestantes();
                                    for( Bloc reste : restes) { %>
                                        <li class="nav-item">
                                            <%= reste.getDesceVolume()%>
                                        </li>    
                                    <% } %>
                                </ul>
                            </td>
                            <!-- Collapse trigger for Estimations -->
                            <td>
                                <div id="estimations-<%= estimation.getIdBloc() %>">
                                    <div class="card card-body">
                                        <strong>P1:</strong> <%= estimation.getEstimationVente() %><br>
                                        <strong>P2:</strong> <%= estimation.getEstimationResteRapportVolumePrix() %><br>
                                        <strong>P3:</strong> <%= estimation.getEstimationResteVolumeMinimal() %>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <li class="nav-item"><a href="etatstock?idBloc=<%= estimation.getIdBloc() %>">Voir detail du stock des dimensions </a></li>
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
