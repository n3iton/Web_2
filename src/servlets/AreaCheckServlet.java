package servlets;

import helpers.JsonParser;
import java.util.LinkedList;
import java.util.List;
import models.ResultData;
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
    String[] xArrStr = (String[]) req.getAttribute("xArr");
    double[] xArr = new double[xArrStr.length];
    for (int i = 0; i < xArrStr.length; i++) {
      xArr[i] = Double.parseDouble(xArrStr[i]);
    }
    double y = (double)req.getAttribute("y");
    double r = (double)req.getAttribute("r");

    Date currentDate = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
    String currentTime = dateFormatter.format(currentDate);
    List<ResultData> listOfResult = new LinkedList<>();

    for (double x : xArr){
      long startTime = System.nanoTime();
      String answer = checkHit(x, y, r) ? "YES" : "NO";
      long processingTime = System.nanoTime() - startTime;
      ResultData result = new ResultData(x, y, r, answer, currentTime, processingTime);
      listOfResult.add(result);
    }

    JsonParser jsonParser = new JsonParser();
    PrintWriter output = resp.getWriter();
    output.println(jsonParser.toJson(listOfResult));
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
