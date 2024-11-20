<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="mg.kodoro.models.Bloc" %>
<%@ page import="mg.kodoro.models.production.Machine" %>
<%
    // Retrieve the list of blocs directly in JSP
    Bloc[] blocList = (Bloc[]) request.getAttribut("bloclist");
    Bloc[] blocOriginals = (Bloc[]) request.getAttribut("originals");
    Machine [] machines = (Machine[]) request.getAttribut("machines");
%>

<section class="container mt-5">
    <div class="row gx-3">
        <div class="col-6 bg-light">
            <h2 class="text-center mb-4" style="color: #FFC107;">Insertion de Bloc</h2>
            <form action="entreebloc" method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="longueur" >Longueur (m)</label>
                    <input type="number" step="0.01" class="form-control" id="longueur" name="longueur" required>
                    <div class="invalid-feedback">Veuillez entrer la longueur du bloc.</div>
                </div>
        
                <div class="form-group">
                    <label for="largeur" >Largeur (m)</label>
                    <input type="number" step="0.01" class="form-control" id="largeur" name="largeur" required>
                    <div class="invalid-feedback">Veuillez entrer la largeur du bloc.</div>
                </div>
        
                <div class="form-group">
                    <label for="epaisseur" >Épaisseur (m)</label>
                    <input type="number" step="0.01" class="form-control" id="epaisseur" name="epaisseur" required>
                    <div class="invalid-feedback">Veuillez entrer l'épaisseur du bloc.</div>
                </div>
        
                <div class="form-group">
                    <label for="prixFabrication" >Prix de Fabrication (Ar)</label>
                    <input type="number" step="0.01" class="form-control" id="prixFabrication" name="prixFabrication" required>
                    <div class="invalid-feedback">Veuillez entrer le prix de fabrication.</div>
                </div>
        
                <div class="form-group">
                    <label for="daty" >Date de Fabrication</label>
                    <input type="date" class="form-control" id="daty" name="daty" required>
                    <div class="invalid-feedback">Veuillez sélectionner la date de fabrication du bloc.</div>
                </div>

                <div class="form-group">
                    <label for="machine">Machine</label>
                    <select class="form-select" id="machine"name="machine" required>
                        <option value="" disabled selected>Choisissez une Machine</option>
                        <!-- BOUCLE POUR LES CHOIX DE MACHINES -->
                        <%
                            if (machines != null && machines.length > 0) {
                                for (Machine mac : machines) {
                        %>
                                    <option value="<%= mac.getIdMachine() %>"><%= mac.getIdMachine() %> - <%= mac.getDesce() %></option>
                        <%
                                }
                            } else {
                        %>
                                <option value="" disabled>Aucune mac disponible</option>
                        <%
                            }
                        %>
                    </select>
                </div>
        
                <button type="submit" class="btn btn-block" style="background-color: #FFC107; color: #FFFFFF;">Insérer le Bloc</button>
            </form>
        </div>
        <div class="col-6 bg-light ">
            <h2 class="text-center  mb-4" style="color: #FFC107;">Mise a jour de prix de revient</h3>
            <form action="updatebloc" method="post">
                <div class="form-group">
                    <label for="blocSelect" >Sélectionnez le Bloc</label>
                    <select class="form-select" id="blocSelect" name="idBloc" required>
                        <option value="" disabled selected>Choisissez un bloc</option>
                        <%
                            if (blocOriginals != null && blocOriginals.length > 0) {
                                for (Bloc bloc : blocOriginals) {
                        %>
                                    <option value="<%= bloc.getIdBloc() %>"><%= bloc.getIdBloc() %> - <%= bloc.getDesce() %></option>
                        <%
                                }
                            } else {
                        %>
                                <option value="" disabled>Aucun bloc Bloc Original a mettre a jour</option>
                        <%
                            }
                        %>
                    </select>
                    <div class="invalid-feedback">Veuillez sélectionner un bloc.</div>
                </div>
                <div class="form-group">
                    <label for="prixFabrication" >Prix de Fabrication (Ar)</label>
                    <input type="number" step="0.01" class="form-control" id="prixFabrication" name="prixFabrication" required>
                    <div class="invalid-feedback">Veuillez entrer le prix de fabrication.</div>
                </div>
                <div>
                    <button type="submit" class="btn btn-block"  style="background-color: #FFC107; color: #FFFFFF;">Mettre a jour</button>
                </div>
            </form>
        </div>
    </div>
</section>
<div class="container">
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
            } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">Aucun bloc trouvé</td>
                </tr>
                <%
            }
            %>
        </tbody>
    </table>
</div>