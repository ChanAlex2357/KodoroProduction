<%@ page import="mg.kodoro.models.Bloc" %>

<%
    // Retrieve the list of blocs directly in JSP
    Bloc[] blocList = Bloc.getAllBlocs();
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Bloc</h2>
    <form action="entreebloc" method="post" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="longueur" style="color: #FFC107;">Longueur (m)</label>
            <input type="number" step="0.01" class="form-control" id="longueur" name="longueur" required>
            <div class="invalid-feedback">Veuillez entrer la longueur du bloc.</div>
        </div>

        <div class="form-group">
            <label for="largeur" style="color: #FFC107;">Largeur (m)</label>
            <input type="number" step="0.01" class="form-control" id="largeur" name="largeur" required>
            <div class="invalid-feedback">Veuillez entrer la largeur du bloc.</div>
        </div>

        <div class="form-group">
            <label for="epaisseur" style="color: #FFC107;">Épaisseur (m)</label>
            <input type="number" step="0.01" class="form-control" id="epaisseur" name="epaisseur" required>
            <div class="invalid-feedback">Veuillez entrer l'épaisseur du bloc.</div>
        </div>

        <div class="form-group">
            <label for="prixFabrication" style="color: #FFC107;">Prix de Fabrication (Ar)</label>
            <input type="number" step="0.01" class="form-control" id="prixFabrication" name="prixFabrication" required>
            <div class="invalid-feedback">Veuillez entrer le prix de fabrication.</div>
        </div>

        <div class="form-group">
            <label for="daty" style="color: #FFC107;">Date de Fabrication</label>
            <input type="date" class="form-control" id="daty" name="daty" required>
            <div class="invalid-feedback">Veuillez sélectionner la date de fabrication du bloc.</div>
        </div>

        <button type="submit" class="btn btn-block" style="background-color: #FFC107; color: #FFFFFF;">Insérer le Bloc</button>
    </form>
</div>

<h3 class="text-center mt-5" style="color: #FFC107;">Liste des Blocs</h3>
<table class="table table-striped mt-3">
    <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Longueur (m)</th>
            <th scope="col">Largeur (m)</th>
            <th scope="col">Épaisseur (m)</th>
            <th scope="col">Prix de Fabrication (Ar)</th>
            <th scope="col">Date de Fabrication</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (blocList != null && blocList.length > 0) {
                for (Bloc bloc : blocList) {
        %>
                    <tr>
                        <td><%= bloc.getIdBloc() %></td>
                        <td><%= bloc.getLongueur() %></td>
                        <td><%= bloc.getLargeur() %></td>
                        <td><%= bloc.getEpaisseur() %></td>
                        <td><%= bloc.getPrixFabrication() %></td>
                        <td><%= bloc.getDateFabrication() %></td>
                    </tr>
        <%
                }
         else {
        %>
                <tr>
                    <td colspan="5" class="text-center">Aucun bloc trouvé</td>
                </tr>
        <%
            }
        %>
    </tbody>
</table>
<div>
    <form action="updatebloc" method="post">
        <div class="form-group">
            <label for="blocSelect" style="color: #FFC107;">Sélectionnez le Bloc</label>
            <select class="form-control" id="blocSelect" name="idBloc" required>
                <option value="" disabled selected>Choisissez un bloc</option>
                <%
                    if (blocList != null && blocList.length > 0) {
                        for (Bloc bloc : blocList) {
                %>
                            <option value="<%= bloc.getIdBloc() %>"><%= bloc.getIdBloc() %> - <%= bloc.getDesce() %></option>
                <%
                        }
                    } else {
                %>
                        <option value="" disabled>Aucun bloc trouvé</option>
                <%
                    }
                %>
            </select>
            <div class="invalid-feedback">Veuillez sélectionner un bloc.</div>
        </div>
        <div class="form-group">
            <label for="prixFabrication" style="color: #FFC107;">Prix de Fabrication (Ar)</label>
            <input type="number" step="0.01" class="form-control" id="prixFabrication" name="prixFabrication" required>
            <div class="invalid-feedback">Veuillez entrer le prix de fabrication.</div>
        </div>
        <div>
            <input type="submit">
        </div>
    </form>
</div>
