<!-- footer.jsp -->
<footer>
    <div class="footer-content">
        <p>&copy; 2024 - Application de Gestion de Kidoro</p>
        <p>Développé par : [ETU002434]</p> <!-- Remplace [Ton Identifiant] par ton identifiant réel -->
        <!-- <p><a href="mailto:support@exemple.com">Contact Support</a> | <a href="terms.jsp">Termes et Conditions</a> | <a href="privacy.jsp">Politique de Confidentialité</a></p> -->
    </div>
</footer>
<jsp:include page='${pageContext.request.contextPath}/pages/static/js.jsp'/>
<script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
<script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
<script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
<script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>      
</body>
</html>