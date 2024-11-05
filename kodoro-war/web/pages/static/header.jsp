<!-- header.jsp -->
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Application Gestion de Blocs</title>
    <title></title>
    <jsp:include page='${pageContext.request.contextPath}/pages/static/css.jsp'/>
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
