<%
    String but = (String)request.getParameter("but");
%>
<!-- header.jsp -->
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Application Gestion de Blocs</title>
    <title></title>
    <jsp:include page='static/css.jsp'/>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="entreebloc">Entrée Bloc</a></li>
                <li><a href="transformation">Transformation</a></li>
                <li><a href="etatstock">État de Stock</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <!-- Inclusion de la page de contenu principale -->
        <% try{ %>
            <jsp:include page='<%=but%>'/>
        <% } catch(Exception e){ out.println(e.getMessage()); } %>
    </main>
    <!-- footer.jsp -->
    <footer>
        <div class="footer-content">
            <p>&copy; 2024 - Application de Gestion de Blocs</p>
            <p>Développé par : [Ton Identifiant]</p> <!-- Remplace [Ton Identifiant] par ton identifiant réel -->
            <p><a href="mailto:support@exemple.com">Contact Support</a> | <a href="terms.jsp">Termes et Conditions</a> | <a href="privacy.jsp">Politique de Confidentialité</a></p>
        </div>
    </footer>
    <jsp:include page='static/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>      
</body>
</html>