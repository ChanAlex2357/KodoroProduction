package kodoro.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kodoro.utils.DispatcherUtils;
import mg.kodoro.models.stock.AdminEtatStockDimension;
import utilitaire.UtilDB;

@WebServlet(name = "EtatStockServlet" , urlPatterns = "/etatstock")
public class EtatStockServlet extends HttpServlet{
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = new UtilDB().GetConn();
            try {
                AdminEtatStockDimension ad = AdminEtatStockDimension.getEtatStock(conn);
                req.setAttribute("adminEtatStockDimension",ad);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("adminEtatStockDimension", null);
            }
            finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        DispatcherUtils.dispatchToTemplate("etatStock.jsp", resp, req);
    }
}
