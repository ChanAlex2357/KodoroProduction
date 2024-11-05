<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <jsp:include page='pages/static/css.jsp'/>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .jumbotron {
            background-color: #FFC107;
            color: #ffffff;
        }
        .btn-yellow {
            background-color: #FFC107;
            color: #ffffff;
            border: none;
        }
        .btn-yellow:hover {
            background-color: #e0a800;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/entreebloc">Entrée Bloc</a></li>
                <li><a href="${pageContext.request.contextPath}/transformation">Transformation</a></li>
                <li><a href="${pageContext.request.contextPath}/etatstock">État de Stock</a></li>
            </ul>
        </nav>
    </header>
<div class="jumbotron text-center">
    <h1 class="display-4">Bienvenue sur la Gestion des Blocs</h1>
    <p class="lead">Gérez facilement vos blocs avec des options d'ajout, de modification et de visualisation.</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-4 text-center">
            <h3>Ajouter un Bloc</h3>
            <p>Accédez au formulaire d'insertion de nouveaux blocs dans la base de données.</p>
            <a href="insererBloc.jsp" class="btn btn-yellow">Ajouter un Bloc</a>
        </div>
        <div class="col-md-4 text-center">
            <h3>Voir les Blocs</h3>
            <p>Consultez la liste des blocs existants et gérez les informations.</p>
            <a href="listeBlocs.jsp" class="btn btn-yellow">Voir les Blocs</a>
        </div>
        <div class="col-md-4 text-center">
            <h3>À propos</h3>
            <p>En savoir plus sur notre système de gestion et ses fonctionnalités.</p>
            <a href="about.jsp" class="btn btn-yellow">À propos</a>
        </div>
    </div>
</div>

    <jsp:include page='pages/static/js.jsp'/>
    <script src="${pageContext.request.contextPath}/apjplugins/champcalcul.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champdate.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/champautocomplete.js" defer></script>      
    <script src="${pageContext.request.contextPath}/apjplugins/addLine.js" defer></script>  
</body>
</html>