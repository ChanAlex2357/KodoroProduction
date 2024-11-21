<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.perf.PerformanceMachine" %>
<%@ page import="java.util.List" %>

<%
    PerformanceMachine[] performances = (PerformanceMachine[]) request.getAttribute("performances");
    if (performances == null || performances.isEmpty()) {
%>
    <h1 class="text-center mt-5" style="color: #FF6F61;">Aucune performance trouvée</h1>
<%
    } else {
%>
<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #4CAF50;">Performances des Machines</h2>
    <table class="table table-hover table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID Machine</th>
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
                <td><%= perf.getPerfTheorique() %></td>
                <td><%= perf.getPerfPratique() %></td>
                <td><%= perf.getEcart() %></td>
            </tr>
        <% 
            } 
        %>
        </tbody>
    </table>
</div>
<%
    }
%>