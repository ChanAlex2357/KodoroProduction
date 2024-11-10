<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="mg.kodoro.models.stock.EtatStockDimension" %>
<%@ page import="mg.kodoro.models.stock.AdminEtatStockDimension" %>

<%
    Object obj = request.getAttribute("adminEtatStockDimension");
    String idBloc = request.getParameter("idBloc");
%>
<%  if(idBloc != null){ %>
    <h1>IDBLOC : <%= idBloc %></h1>
<% } %>
<!-- SI il n'y a pas de stock disponible -->
<%  if(obj == null){ %>
        <h2>Aucnune Stock Disponible</h1>
<%  }
    else
    {
        AdminEtatStockDimension adminEtat = (AdminEtatStockDimension) obj;
%>

<div class="container mt-5">
    <h2 class="text-center mb-4" style="color: #FFC107;">État de Stock des Dimensions</h2>
    <%= adminEtat.getHtml() %>
</div>
<%    }
%>
