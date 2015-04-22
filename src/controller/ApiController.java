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
			
			int min = 0;
			int max = Integer.MAX_VALUE;
			Pattern p = Pattern.compile("\\d+");
			
			if(minPrice != null && !minPrice.isEmpty()) {
				Matcher m = p.matcher(minPrice);
				if (m.matches()) {
					min = Integer.parseInt(minPrice);
				}
			}
			
			if(maxPrice != null && !maxPrice.isEmpty()) {
				Matcher m = p.matcher(maxPrice);
				if (m.matches()) {
					max = Integer.parseInt(maxPrice);
				}
			}
			
			pizzas = stmts.getPizzas(min, max);
			
			String xml = "<?xml version='1.0' encoding='UTF-8'?>";
			xml += "<collection>";
			for (Pizza pizza : pizzas) {
				xml += "<pizza>";
				xml += "<name>" + pizza.getName() + "</name>";
				xml += "<price>" + pizza.getPrice() + "</price>";
				xml += "<description>" + pizza.getDescription() + "</description>";
				xml += "</pizza>";
			}
			xml += "</collection>";
			
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
