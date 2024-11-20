<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mg.kodoro.models.production.Machine" %>
<%
    // Récupération des données depuis les attributs de la requête
    Machine[] machines = (Machine[]) request.getAttribute("machines");
%>

<section class="container mt-5">
    <div class="row gx-3">
        <!-- Formulaire pour insérer une nouvelle machine -->
        <div class="col-6 bg-light">
            <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Machine</h2>
            <form action="machine" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="descriptionMachine">Description</label>
                    <input type="text" class="form-control" id="descriptionMachine" name="desce" required>
                    <div class="invalid-feedback">Veuillez entrer une description.</div>
                </div>
                <button type="submit" class="btn btn-block" style="background-color: #FFC107; color: #FFFFFF;">Ajouter la Machine</button>
            </form>
        </div>

        <!-- Liste des machines -->
        <div class="col-6 bg-light">
            <h2 class="text-center mb-4" style="color: #FFC107;">Liste des Machines</h2>
            <table class="table table-striped mt-3">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Description</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (machines != null && machines.length > 0) {
                            for (Machine machine : machines) {
                    %>
                                <tr>
                                    <td><%= machine.getIdMachine() %></td>
                                    <td><%= machine.getDesce() %></td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="4" class="text-center">Aucune machine trouvée</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</section>
