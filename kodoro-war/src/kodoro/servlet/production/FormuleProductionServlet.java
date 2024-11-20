package kodoro.servlet.production;

import mg.kodoro.models.annexe.Ressource;
import mg.kodoro.models.production.AdminFormuleProduction;
import mg.kodoro.models.production.FormuleProduction;
import utilitaire.UtilDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "FormuleProductionServlet",urlPatterns="/formule")
public class FormuleProductionServlet extends HttpServlet {

    // Méthode GET : Affiche le formulaire de création de formule
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ressource [] ressources = null;
        Connection conn = new UtilDB().GetConn();
        try {
            ressources = Ressource.getAllRessources(conn);
        } 
        catch (Exception e) {e.printStackTrace();}
        finally {
            try {
                conn.close();
            } catch (Exception e) {e.printStackTrace();}
        }
        // Passe la liste des ressources à la page JSP
        request.setAttribute("ressourceList", ressources);
        // Redirige vers la page JSP
        DispatcherUtils.dispatchToTemplate("formule.jsp",response,request);
    }

    // Méthode POST : Traite la création de la formule de production
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère les données du formulaire
        String description = request.getParameter("desce");
        String[] idRessources = request.getParameterValues("idRessource[]");
        String[] quantites = request.getParameterValues("quantite[]");
        Connection conn = new UtilDB().GetConn();
        FormuleProduction formule = null;
        try {
            AdminFormuleProduction adminFormuleProduction = new AdminFormuleProduction(description, idRessources, quantites);
            formule = adminFormuleProduction.genererFormule(conn);
        } 
        catch (Exception e) {
            e.printStackTrace();
            DispatcherUtils.dispatchToError(e.getMessage(),"formule", response, request);
        }
        finally {
            try {
                conn.close();
            } catch (Exception e) {e.printStackTrace();}
        }
        // Affiche une confirmation
        request.setAttribute("message", "La formule a été créée avec succès !");
        request.setAttribute("formule", formule);
        // Redirige vers une page de confirmation ou un récapitulatif
        DispatcherUtils.dispatchToTemplate("formuleConfirmation.jsp",response,request);
    }
}
