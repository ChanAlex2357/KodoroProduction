package kodoro.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherUtils {
    public static void dispatchToTemplate(String but , HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException{
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("but", but);
        req.getRequestDispatcher( "pages/template.jsp").forward(req, resp);
    }
    public static void dispatchToError(String message , String redirectUrl , HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute("message",message);
        req.setAttribute("redirectUrl", redirectUrl);
        req.getRequestDispatcher( "pages/errorPage.jsp").forward(req, resp);
    }
}
