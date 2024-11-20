package kodoro.servlet.production;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.blockggen.AdminBlocGen;
import utilitaire.UtilDB;

@WebServlet(name = "BlocGen"  , urlPatterns = "/genbloc")
public class blocGenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DispatcherUtils.dispatchToTemplate("blocGen.jsp", resp, req);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /// Recuperation des donnees pour la generation des donnees
        String quantite = req.getParameter("quantite");
        /// Longueur
        String Lmin = req.getParameter("Lmin");
        String Lmax = req.getParameter("Lmax");
        /// Lareur
        String lmin = req.getParameter("lmin");
        String lmax = req.getParameter("lmax");
        /// Epaisseur
        String emin = req.getParameter("emin");
        String emax = req.getParameter("emax");
        /// Annnee
        String amin = req.getParameter("amin");
        String amax = req.getParameter("amax");
        /// Marge
        String marge = req.getParameter("marge");

        // * Generer les Blocs de donnees

        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            AdminBlocGen adminBlocGen = new AdminBlocGen(quantite, Lmin, Lmax, lmin, lmax, emin, emax, amin, amax, marge);
            adminBlocGen.generate(conn);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            DispatcherUtils.dispatchToError(e.getMessage(),"genbloc",resp,req);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        resp.sendRedirect("genbloc");

    }
}
