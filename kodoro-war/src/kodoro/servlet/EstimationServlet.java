package kodoro.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.estimation.AdminEstimation;
import utilitaire.UtilDB;

@WebServlet(name = "EstimationServlet" , urlPatterns = "/estimation")
    public class EstimationServlet extends HttpServlet{
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection conn = new UtilDB().GetConn();
            try {
                AdminEstimation adminEstimation = AdminEstimation.getEstimations(conn);
                req.setAttribute("adminestimation", adminEstimation);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("adminestimation", null);
            }
            finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            DispatcherUtils.dispatchToTemplate("estimation.jsp", resp, req);
    }
}
