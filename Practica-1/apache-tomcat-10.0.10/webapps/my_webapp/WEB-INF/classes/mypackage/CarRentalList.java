package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import jakarta.servlet.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import jakarta.servlet.http.*;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import org.json.simple.parser.ParseException;

public class CarRentalList extends HttpServlet {
  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    String contra = req.getParameter("password");

    if (nombre.equals("admin") && contra.equals("admin")){
      out.println("<h1><bold>Rentals:</bold></h1>");
      JSONParser pars = new JSONParser();

      Path currentRelativePath = Paths.get("");
      String path_to_tomcat = currentRelativePath.toAbsolutePath().toString();
      try (Reader r = new FileReader("database.json")){
        JSONArray jsonArray = (JSONArray) pars.parse(r);
        Iterator it = jsonArray.iterator();
        while (it.hasNext()){
          JSONObject jso = (JSONObject) it.next();
          out.println("CO2 Rating: " + jso.get("CO2_rating") + "<br>");
          out.println("Engine: " + jso.get("engine") + "<br>");
          out.println("Number of days: " + jso.get("num_dies") + "<br>");
          out.println("Number of units: " + jso.get("num_unitats") + "<br>");
          out.println("Discount: " + jso.get("descompte") + "<br>");
          out.println("<br>" + "<br>");
        }
        out.println("<a href='carrental_home.html'>Home</a>");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      catch (ParseException p){
        p.printStackTrace();
      }
  }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
