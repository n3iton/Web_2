package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    double x = Double.parseDouble(req.getParameter("x"));
    double y = Double.parseDouble(req.getParameter("y"));
    double r = Double.parseDouble(req.getParameter("r"));
    req.setAttribute("x", x);
    req.setAttribute("y", y);
    req.setAttribute("r", r);
    getServletContext().getRequestDispatcher("/check").forward(req,resp);
  }
}
