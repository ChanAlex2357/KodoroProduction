package kodoro.servlet.production;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;

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

    }
}
