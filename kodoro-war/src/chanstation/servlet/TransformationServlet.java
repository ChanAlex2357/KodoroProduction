package chanstation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TransformationServlet" , urlPatterns = "/transformation")
public class TransformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperation de la liste des blocs existantes
        req.getRequestDispatcher( "pages/pageContent.jsp?but=transformation.jsp").forward(req, resp);
    }
}
