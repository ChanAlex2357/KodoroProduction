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
import mg.kodoro.models.annexe.Ressource;
import mg.kodoro.models.annexe.Unite;
import utilitaire.UtilDB;

@WebServlet(name = "RessourceServlet", urlPatterns = "/ressource")
public class RessourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ressource[] ressources = null;
        Unite[] uniteList = null; // Liste des unités
        Connection conn = new UtilDB().GetConn();

        try {
            ressources = (Ressource[]) Ressource.getAllRessources(conn); // Récupérer toutes les ressources
            uniteList = (Unite[]) Unite.getAllUnite(conn); // Récupérer toutes les unités
        } catch (Exception e) {
            e.printStackTrace();
            DispatcherUtils.dispatchToError(e.getMessage(), "ressource", resp, req);
            return;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Ajouter les données dans les attributs de requête
        req.setAttribute("ressources", ressources);
        req.setAttribute("uniteList", uniteList);

        // Dispatch vers la JSP
        DispatcherUtils.dispatchToTemplate("ressource.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String desce = req.getParameter("desce");
        String idUnite = req.getParameter("idUnite");
        String puAchatStr = req.getParameter("puAchat");

        if (desce == null || idUnite == null || puAchatStr == null) {
            DispatcherUtils.dispatchToError("Paramètres invalides", "ressource", resp, req);
            return;
        }

        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);

            // Créer une nouvelle ressource
            Ressource ressource = new Ressource(desce, puAchatStr, idUnite);
            ressource.createObject(conn);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            DispatcherUtils.dispatchToError(e.getMessage(), "ressource", resp, req);
            return;
        } finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        // Redirection vers la page des ressources
        resp.sendRedirect("ressource");
    }
}
