<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.perf.PerformanceMachine" %>

<%
    PerformanceMachine[] performances = (PerformanceMachine[]) request.getAttribute("performances");
    String year = request.getParameter("year");
%>

<div class="container mt-5">
    <!-- Formulaire pour filtrer par années -->
    <form method="get" action="performanceMachine" class="form-inline mb-4">
        <div class="form-group mx-sm-3">
            <label for="year" class="mr-2">Année:</label>
            <input type="number" name="year" id="year" class="form-control" 
            value="<%= year != null ? year : "" %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Filtrer</button>
    </form>

    <%
        if (performances == null || performances.length == 0) {
    %>
        <h1 class="text-center mt-5" style="color: #FF6F61;">Aucune performance trouvée</h1>
    <%
        } else {
    %>
        <h2 class="text-center mb-4" style="color: #4CAF50;">Performances des Machines <%
            if(year != null) { %>
                (<%=year%>)
        <% }    
        %></h2>
        <table class="table table-hover table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID Machine</th>
                    <th>Qte Produit</th>
                    <th>Performance Théorique</th>
                    <th>Performance Pratique</th>
                    <th>Écart</th>
                </tr>
            </thead>
            <tbody>
            <% 
                for (PerformanceMachine perf : performances) {
            %>
                <tr>
                    <td><%= perf.getIdMachine() %></td>
                    <td><%= perf.getQteProduit() %></td>
                    <td><%= perf.getPerfTheorique() %></td>
                    <td><%= perf.getPerfPratique() %></td>
                    <td><%= perf.getEcart() %></td>
                </tr>
            <% 
                } 
            %>
            </tbody>
        </table>
    <%
        }
    %>
</div>
