package kodoro.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.Bloc;
import utilitaire.UtilDB;

@WebServlet(name = "updateBlocServlet" , urlPatterns = "updatebloc")
public class UpdateBlocServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idBloc = request.getParameter("idBloc");
        String prixFabricationStr = request.getParameter("prixFabrication");

        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);

            Bloc b = Bloc.getById(idBloc, conn);
            b.updatePrixFabrication(prixFabricationStr, conn);
            conn.commit();
            response.sendRedirect("entreebloc");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception r) {
                r.printStackTrace();
            }
            e.printStackTrace();
            DispatcherUtils.dispatchToError(e.getMessage(), "transformation",response,request);

        }
        finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
