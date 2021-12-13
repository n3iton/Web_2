package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String[] xArrStr = req.getParameterValues("xArr[]");
    System.out.println(xArrStr);
    double y = Double.parseDouble(req.getParameter("y"));
    double r = Double.parseDouble(req.getParameter("r"));

    req.setAttribute("xArr", xArrStr);
    req.setAttribute("y", y);
    req.setAttribute("r", r);
    getServletContext().getRequestDispatcher("/check").forward(req,resp);
  }
}
