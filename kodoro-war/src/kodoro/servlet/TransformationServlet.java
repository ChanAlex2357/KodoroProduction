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
import mg.kodoro.models.DimensionUsuels;
import mg.kodoro.models.transformation.TransformationCPL;
import utilitaire.UtilDB;

@WebServlet(name = "TransformationServlet", urlPatterns = "/transformation")
public class TransformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bloc[] blocList = null; // La liste de tous les blocs
        DimensionUsuels[] dimensionUsuels = null;
        Connection conn = new UtilDB().GetConn();
        try {
            blocList = Bloc.getAllBlocs(conn); // La liste de tous les blocs
            dimensionUsuels = DimensionUsuels.getAllDimensionsUsuelles(conn); // La liste des blocs originales
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("bloclist", blocList);
        req.setAttribute("dimensions", dimensionUsuels);
        DispatcherUtils.dispatchToTemplate("transformation.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupération des données de transformation
        String idBloc = req.getParameter("idBloc");
        String margePourcentage = req.getParameter("margePourcentage");
        String dateTransformation = req.getParameter("dateTransformation");

        // Récupération des détails de transformation (idDimensionUsuel, quantités, prixRevient)
        String[] idDimensions = req.getParameterValues("idDimensionUsuel[]");
        String[] quantites = req.getParameterValues("quantite[]");
        String[] prixRevient = req.getParameterValues("prixRevient[]");
        String[] prixRevientunitaire = req.getParameterValues("prixRevientunitaire[]");

        // Récupération des blocs restants (longueurs, largeurs, épaisseurs)
        String[] longueurs = req.getParameterValues("longueurBlocRestant[]");
        String[] largeurs = req.getParameterValues("largeurBlocRestant[]");
        String[] epaisseurs = req.getParameterValues("epaisseurBlocRestant[]");

        // Gestion des données et validation si nécessaire
        if (idBloc != null && margePourcentage != null) {
            Connection conn = new UtilDB().GetConn();
            try {
                conn.setAutoCommit(false);
                TransformationCPL trasCPL = new TransformationCPL(idBloc, margePourcentage,dateTransformation, idDimensions, quantites, prixRevientunitaire,prixRevient, longueurs, largeurs, epaisseurs);
                trasCPL.validerTransformation(conn);
                conn.commit();

                resp.sendRedirect("transformation");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                String message = "Les détails de transformation sont incomplets \n"+e.getMessage();
                DispatcherUtils.dispatchToError(message, "transformation",resp,req);

            }
            finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les données de transformation sont incomplètes.");
        }
    }
}
