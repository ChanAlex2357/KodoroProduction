package kodoro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.Bloc;
import mg.kodoro.models.achat.Achat;
import mg.kodoro.models.annexe.Produit;
import mg.kodoro.models.production.AdminProduction;
import mg.kodoro.models.production.Machine;
import utilitaire.UtilDB;

public class AchatServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produit[] produits = null;

        Connection conn = new UtilDB().GetConn();
        try {
            produits = Produit.getAllProduits(conn);
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

        req.setAttribute("produits", produits);
        DispatcherUtils.dispatchToTemplate("achat.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String qte = req.getParameter("quantite");
        String puAchat = req.getParameter("puAchat");
        String dateAchat = req.getParameter("dateAchat");
        String idProduit = req.getParameter("idProduit");

        Connection local = new UtilDB().GetConn();
        String message = null;
        // Connection remote = DbUtils.getRemoteConn();
        try {
            // Desactiver l'autocommit
            local.setAutoCommit(false);

            Achat achat = new Achat(idProduit, qte,puAchat, dateAchat);
            achat.createObject(local);
            // Commiter les changements
            local.commit();
        
        } catch (Exception e) {
            try {
                local.rollback();
                // remote.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            message = "Unexpected error: " + e.getMessage();
            DispatcherUtils.dispatchToError(message, "achat",resp,req);
        }
        finally {
            try {
                local.close();
                // remote.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("achat");
    }
}

