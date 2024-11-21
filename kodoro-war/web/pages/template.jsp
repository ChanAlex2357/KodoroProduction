<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String but = (String) request.getAttribute("but");
    String title = (String) request.getAttribute("title");
    String message = (String) request.getAttribute("message"); // Retrieve the message attribute if it exists
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Application Gestion de Blocs</title>
    <title><%=title%></title>
    <jsp:include page='static/css.jsp'/>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">

                <a class="navbar-brand" href="#">KODORO</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="entreebloc">Entrée Bloc</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="transformation">Transformation</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="estimation">Estimations</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="etatstock">Etat de stock</a>
                        </li>
                        <li class="nav-iterm">
                            <a class="nav-link" href="genbloc">Generer Bloc</a>
                        </li>
                        <li class="nav-iterm">
                            <a class="nav-link" href="machine">Machine</a>
                        </li>
                        <li class="nav-iterm">
                            <a class="nav-link" href="ressource">Ressource</a>
                        </li>
                        <li class="nav-iterm">
                            <a class="nav-link" href="unite">Unite</a>
                        </li>
                        <li class="nav-iterm">
                            <a class="nav-link" href="performanceMachine">Performance Machine</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <main>
        <!-- Display message if present -->
        <% if (message != null && !message.isEmpty()) { %>
            <script type="text/javascript">
                alert("<%= message %>");
            </script>
        <% } %>

        <!-- Inclusion de la page de contenu principale -->
        <% try { %>
            <jsp:include page='<%=but%>'/>
        <% } catch(Exception e) { out.println(e.getMessage()); } %>
    </main>

    <footer class="py-3 bg-light" >
        <div class="container">
            <p>&copy; 2024 - Application de Gestion de Blocs</p>
            <p><h3>ETU002434</h3></p> <!-- Replace [Ton Identifiant] with your actual identifier -->
        </div>
    </footer>

    <jsp:include page='static/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>      
</body>
</html>
