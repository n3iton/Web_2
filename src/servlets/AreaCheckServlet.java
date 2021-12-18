package servlets;

import helpers.JsonParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;
import javax.servlet.ServletContext;
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
  List<ResultData> resultsFromAllResponses = Collections.synchronizedList(new ArrayList<>());
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      PrintWriter output = resp.getWriter();
      try {
        String[] xArrStr = (String[]) req.getAttribute("xArr");
        double[] xArr = new double[xArrStr.length];
        for (int i = 0; i < xArrStr.length; i++) {
          xArr[i] = Double.parseDouble(xArrStr[i]);
        }
        double y = (double) req.getAttribute("y");
        double r = (double) req.getAttribute("r");
        if (!validateR(r)) {
          throw new NumberFormatException();
        }
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormatter.format(currentDate);
        List<ResultData> listOfResult = new ArrayList<>();

        for (double x : xArr) {
          long startTime = System.nanoTime();
          String answer = checkHit(x, y, r) ? "YES" : "NO";
          long processingTime = System.nanoTime() - startTime;
          ResultData result = new ResultData(x, y, r, answer, currentTime, processingTime);
          listOfResult.add(result);
        }

        ServletContext context = this.getServletContext();
        if (context.getAttribute("results") == null) {
          context.setAttribute("results", resultsFromAllResponses);
        }
        resultsFromAllResponses.addAll(listOfResult);
        context.setAttribute("results", resultsFromAllResponses);

        JsonParser jsonParser = new JsonParser();
        output.println(jsonParser.toJson(listOfResult));
      } catch (NumberFormatException e) {
        output.print("Invalid-data");
        e.printStackTrace();
      } finally {
        output.close();
      }
  }

  private boolean checkHit(double x, double y, double r) {
    boolean result = false;
    if (x >= 0 && y <= 0)
      result = checkRectangleHit(x, y, r);
    else if (x <= 0 && y <= 0)
      result = checkTriangleHit(x, y, r);
    else if (x <= 0 && y >= 0)
      result = checkCircleHit(x, y, r);
    return result;
  }

  private boolean checkRectangleHit(double x, double y, double r) {
    return x <= r && Math.abs(y) <= r / 2;
  }

  private boolean checkTriangleHit(double x, double y, double r) {
    return r + x >= 2 * Math.abs(y);
  }

  private boolean checkCircleHit(double x, double y, double r) {
    return Math.sqrt(x * x + y * y) <= r / 2;
  }

  private boolean validateR(double r) {
    List<Double> correctRValues = Arrays.asList(1d, 1.5d, 2d, 2.5d, 3d);
    if (!correctRValues.contains(r))
      return false;
    return true;
  }
}
