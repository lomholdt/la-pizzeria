package controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.parser.Parser;

import model.FlashMessage;
import model.Pizza;
import model.Statements;

/**
 * Servlet implementation class BrowseController
 */
@WebServlet("/BrowseController")
public class BrowseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			new FlashMessage().getFlashMessage(request, "msg"); // Flash message hack for servlets! :) 
			
			int page = 1;
			int pizzasPerPage = 10; 
			if(request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			String sortOrder = sortBy(request);
			
			int offset = (page-1) * pizzasPerPage;
			
			Statements stmts = new Statements();
			
			String minPrice = request.getParameter("minPrice");
			String maxPrice = request.getParameter("maxPrice");
			List<Pizza> pizzas;
			
			int totalPizzas = stmts.getNumPizzas();
			
			if(minPrice != null && !minPrice.isEmpty() && maxPrice != null && !maxPrice.isEmpty()) {
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(minPrice);
				Matcher m1 = p.matcher(maxPrice);
				if (!m.matches() || !m1.matches()) {
					RequestDispatcher view = request.getRequestDispatcher("views/pizza/browse.jsp");
					request.setAttribute("error", "Minimum price or max price ERROR...<br>");
					view.forward(request, response);
					return;
				}
				pizzas = stmts.getPizzas(offset, pizzasPerPage, sortOrder, Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
				totalPizzas = stmts.getNumPizzas(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
			}
			else {
				pizzas = stmts.getPizzas(offset, pizzasPerPage, sortOrder);	
				totalPizzas = stmts.getNumPizzas();
			}
			
			int totalPages = (int)Math.ceil(totalPizzas * 1.0 / pizzasPerPage);
			request.setAttribute("pizzas", pizzas);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			
		} catch (Exception e) {
			request.setAttribute("error", "Could not get pizzas...<br>" + e.getMessage());
		}
		
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/browse.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected String sortBy(HttpServletRequest request){
		String urlSort = request.getParameter("sortBy");
		
		if(urlSort == null){
			request.setAttribute("sortBy", "name");
			return "name";
		}
		else{
			request.setAttribute("sortBy", urlSort);
			return urlSort;
		}
	}

}
