<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <jsp:include page='pages/elements/css.jsp'/>
</head>
<body>
    <header>
        <nav class="navbar nav">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">kodoro</a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/prelevementPompe" 
                            class="nav-link">
                            Prelevement Pompe
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/gen-data">
                            Generer les donnees
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <h1>WELCOME TO THE kodoro</h1>

    <jsp:include page='pages/elements/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>  
</body>
</html>