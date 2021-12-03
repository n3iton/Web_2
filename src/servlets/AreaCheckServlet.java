package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AreaCheckServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    long nanoTime = System.nanoTime();
    double x = (double)req.getAttribute("x");
    double y = (double)req.getAttribute("y");
    double r = (double)req.getAttribute("r");

    PrintWriter output = resp.getWriter();
    Date currentDate = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
    String currentTime = dateFormatter.format(currentDate);
    output.println(currentTime);
  }

  private boolean checkHit(double x, double y, double r) {
    boolean result = false;
    if (x >= 0 && y <= 0)
      result = checkRectangleHit(x, y, r);
    else if (x <= 0 && y >= 0)
      result = checkCircleHit(x, y, r);
    else if (x <= 0 && y <= 0)
      result = checkTriangleHit(x, y, r);
    return result;
  }

  private boolean checkRectangleHit(double x, double y, double r) {
    boolean result = false;
    if (x <= r && Math.abs(y) <= r/2)
      result = true;
    return result;
  }

  private boolean checkTriangleHit(double x, double y, double r) {
    boolean result = false;
    if (r + x >= 2 * Math.abs(y))
      result = true;
    return result;
  }

  private boolean checkCircleHit(double x, double y, double r) {
    boolean result = false;
    if (Math.sqrt(x*x + y*y) <= r/2)
      result = true;
    return result;
  }
}
