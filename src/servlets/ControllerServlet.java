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
    try {
      resp.setCharacterEncoding("UTF-8");
      if (req.getParameterValues("xArr[]") != null && req.getParameter("y") != null
          && req.getParameter("r") != null) {
        String[] xArrStr = req.getParameterValues("xArr[]");
        double y = Double.parseDouble(req.getParameter("y"));
        double r = Double.parseDouble(req.getParameter("r"));

        req.setAttribute("xArr", xArrStr);
        req.setAttribute("y", y);
        req.setAttribute("r", r);
        getServletContext().getRequestDispatcher("/check").forward(req, resp);
      } else {
        getServletContext().getRequestDispatcher("/reload").forward(req, resp);
      }
    } catch (Exception e) {
      e.printStackTrace();
      getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
    }
  }
}
