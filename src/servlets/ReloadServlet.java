package servlets;

import helpers.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ResultData;

public class ReloadServlet  extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter output = resp.getWriter();
    ServletContext context = this.getServletContext();
    if (context.getAttribute("results") == null) {
      context.setAttribute("results", Collections.synchronizedList(new ArrayList<>()));
    }
    List<ResultData> results = (List<ResultData>) context.getAttribute("results");
    JsonParser parser = new JsonParser();
    output.write(parser.toJson(results));
  }
}
