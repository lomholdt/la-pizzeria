package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pizza;
import model.Statements;

/**
 * Servlet implementation class ApiController
 */
@WebServlet("/ApiController")
public class ApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Statements stmts = new Statements();
			
			String minPrice = request.getParameter("minPrice");
			String maxPrice = request.getParameter("maxPrice");
			List<Pizza> pizzas;
			
			
			if(minPrice != null && !minPrice.isEmpty() && maxPrice != null && !maxPrice.isEmpty()) {
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(minPrice);
				Matcher m1 = p.matcher(maxPrice);
				if (!m.matches() || !m1.matches()) {
					return;
				}
				pizzas = stmts.getPizzas(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
			}
			else {
				pizzas = stmts.getPizzas();	
			}
			String xml = "<?xml version='1.0' encoding='UTF-8'?>";
			xml += "<p:collection xmlns:p='http://pizza.dev' "
					+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
					+ "xsi:schemaLocation='http://46.101.8.79/xml pizza-scheme.xsd'>";
			for (Pizza pizza : pizzas) {
				xml += "<p:pizza>";
				xml += "<name>" + pizza.getName() + "</name>";
				xml += "<price>" + pizza.getPrice() + "</price>";
				xml += "<description>" + pizza.getDescription() + "</description>";
				xml += "</p:pizza>";
			}
			xml += "</p:collection>";
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
