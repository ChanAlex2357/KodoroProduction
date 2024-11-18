<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Optional" %>

<%
    // Récupération des paramètres pour personnaliser le message d'erreur et l'URL de redirection
    String errorMessage = Optional.ofNullable((String)request.getAttribute("message")).orElse("Une erreur est survenue.");
    String redirectUrl = Optional.ofNullable((String)request.getAttribute("redirectUrl")).orElse("index.jsp"); // Redirection par défaut vers l'accueil
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur</title>
    <jsp:include page='static/css.jsp'/>
    <style>
        .container {
            margin-top: 100px;
            max-width: 600px;
            text-align: center;
        }
        .btn-custom {
            background-color: #FFC107;
            color: #FFFFFF;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-danger">Erreur</h1>
    <p class="lead"><%= errorMessage %></p>
    <a class="btn btn-custom mt-3" href='<%= redirectUrl %>'>
        OK
    </a>
</div>
</body>
</html>
