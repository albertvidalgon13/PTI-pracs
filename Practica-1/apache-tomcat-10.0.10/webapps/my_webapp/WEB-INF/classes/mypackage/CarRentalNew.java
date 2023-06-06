package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import org.json.simple.parser.ParseException;

public class CarRentalNew extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("name");
    cont ++;
    String CO2_rating = req.getParameter("co2_rating");
    String engine = req.getParameter("sub_model_vehicle");
    int num_days = Integer.parseInt(req.getParameter("dies_lloguer"));
    int num_units = Integer.parseInt(req.getParameter("num_vehicles"));
    double discount = Double.parseDouble(req.getParameter("descompte"));

    //COMPROVACIO ERRORS
    boolean correct_input = true;
    if (num_days == 0 || discount > 100.0 || num_units == 0){
        correct_input = false;
    }

    else {
        JSONObject jsonquery = new JSONObject();
        jsonquery.put("CO2_rating", CO2_rating);
        jsonquery.put("engine", engine);
        jsonquery.put("num_dies", new Integer(num_days));
        jsonquery.put("num_unitats", new Integer(num_units));
        jsonquery.put("descompte", new Double(discount));
        JSONArray list = new JSONArray();
        out.println("<html><body><h3>CO2 Rating: " + CO2_rating + "</h3><h3>Engine: " + engine + "</h3><h3>Number of days: " + num_days + "</h3><h3>Number of units: " + num_units + "</h3><h3>Discount: " + discount + "</h3><br><br><a href='carrental_home.html'>Home</a><br></body></html>");

        File f = new File("database.json");
        if (f.exists()){
          try (Reader r = new FileReader("database.json")){
            JSONParser parser = new JSONParser();
            list = (JSONArray) parser.parse(r);
          }
          catch (ParseException e) {
            e.printStackTrace();
          }
        }
        list.add(jsonquery);

        try (FileWriter file = new FileWriter("database.json")){
          file.write(list.toJSONString());
        }
        catch(IOException e){ e.printStackTrace(); }
    }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
