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
import mg.kodoro.models.annexe.Unite;
import utilitaire.UtilDB;

@WebServlet(name = "UniteServlet", urlPatterns = "/unite")
public class UniteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Unite[] unites = null;
        Connection conn = new UtilDB().GetConn();

        try {
            // Récupérer toutes les unités disponibles
            unites = Unite.getAllUnite(conn);
        } catch (Exception e) {
            e.printStackTrace();
            DispatcherUtils.dispatchToError(e.getMessage(), "unite", resp, req);
            return;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Ajouter la liste des unités à la requête
        req.setAttribute("unites", unites);

        // Dispatch vers la page JSP
        DispatcherUtils.dispatchToTemplate("unite.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String val = req.getParameter("val");
        String desce = req.getParameter("desce");

        // Vérification des paramètres
        if (val == null || desce == null || val.isEmpty() || desce.isEmpty()) {
            DispatcherUtils.dispatchToError("Les paramètres val et desce sont obligatoires.", "unite", resp, req);
            return;
        }

        Connection conn = new UtilDB().GetConn();

        try {
            conn.setAutoCommit(false);

            // Création d'une nouvelle unité
            Unite unite = new Unite(val, desce);
            unite.createObject(conn);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DispatcherUtils.dispatchToError(e.getMessage(), "unite", resp, req);
            return;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirection vers la liste des unités
        resp.sendRedirect("unite");
    }
}
