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
import mg.kodoro.models.production.Machine;
import utilitaire.UtilDB;
@WebServlet(name = "MachineServlet" , urlPatterns = "/machine")
public class MachineServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Machine[] machines = Machine.getAllMachines(); //La liste des machines
        req.setAttribute("machines", machines);
        DispatcherUtils.dispatchToTemplate("machine.jsp", resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String desce = req.getParameter("desce");

        Connection conn = new UtilDB().GetConn();
        try {
            conn.setAutoCommit(false);
            Machine machine = new Machine(desce);
            machine.createObject(conn);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            DispatcherUtils.dispatchToError(e.getMessage(),"machine",resp,req);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        resp.sendRedirect("machine");
    }
}
