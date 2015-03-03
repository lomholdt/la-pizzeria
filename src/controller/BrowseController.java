package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		
		int page = 1;
		int pizzasPerPage = 10; 
		if(request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
		String sortBy = sortBy(request);
		
		int offset = (page-1)*pizzasPerPage;
		
		Statements stmts = new Statements();
		List<Pizza> pizzas = stmts.getPizzas(offset, pizzasPerPage, sortBy);
		
		int totalPizzas = stmts.getNumPizzas();
		int totalPages = (int)Math.ceil(totalPizzas * 1.0 / pizzasPerPage);
		
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("page", page);
		
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
