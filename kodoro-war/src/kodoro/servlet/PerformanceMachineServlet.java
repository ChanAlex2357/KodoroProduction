package kodoro.servlet;

import mg.kodoro.models.perf.PerformanceMachine;
import utilitaire.UtilDB;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;


@WebServlet(name = "PerformanceMachineServlet",urlPatterns = "/performanceMachine")
public class PerformanceMachineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String year = request.getParameter("year");
        Connection conn = new UtilDB().GetConn();
        try {
            // Récupération des performances
            PerformanceMachine[] performances = PerformanceMachine.getAllPerformanceMachines(year,conn);
            // Attachement des données à la requête
            request.setAttribute("performances", performances);
            // Redirection vers la JSP
            DispatcherUtils.dispatchToTemplate("perfMachine.jsp",response,request);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des performances");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {}
            }
        }
    }
}
