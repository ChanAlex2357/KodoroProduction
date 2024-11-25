package kodoro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.Bloc;
import mg.kodoro.utils.DbUtils;
import utilitaire.UtilDB;


@WebServlet(name = "EntreeBlocServlet" , urlPatterns = "/entreebloc")
public class EntreeBlocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DispatcherUtils.dispatchToTemplate("entreeBloc.jsp", resp, req);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Set request encoding to UTF-8 to handle special characters
    request.setCharacterEncoding("UTF-8");

    // Retrieve form parameters
    String longueurStr = request.getParameter("longueur");
    String largeurStr = request.getParameter("largeur");
    String epaisseurStr = request.getParameter("epaisseur");
    String prixFabricationStr = request.getParameter("prixFabrication");
    String dateFabricationStr = request.getParameter("daty");

    // Prepare response to show success or failure
    String message = null;
    Connection local = new UtilDB().GetConn();
    Connection remote = DbUtils.getRemoteConn();
    try {
        Bloc bloc = new Bloc(longueurStr, largeurStr, epaisseurStr, dateFabricationStr, prixFabricationStr);
        // Desactiver l'autocommit
        local.setAutoCommit(false);
        remote.setAutoCommit(false);

        bloc.createObject(local, remote);

        // Commiter les changements
        local.commit();
        remote.commit();
    } catch (Exception e) {
        try {
            local.rollback();
            remote.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        message = "Unexpected error: " + e.getMessage();
        DispatcherUtils.dispatchToError(message, "entreebloc",response,request);
    }
    finally {
        try {
            local.close();
            remote.close();
            System.out.println("-- Connections Closed on insertion bloc --");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    System.out.println(" ------ MESSAGE ------ \n"+message+"\n ----------------------");
    response.sendRedirect("entreebloc");
}

}
