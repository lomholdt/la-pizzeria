package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Authenticator;
import model.FlashMessage;
import model.Statements;
import model.User;

/**
 * Servlet implementation class AddPizzaController
 */
@WebServlet("/PizzaController")
public class PizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Authenticator auth = new Authenticator();
	Statements s = new Statements();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!isAllowed(request)){
			response.sendRedirect("browse"); 
			return;
		}
		
		String removeId = request.getParameter("remove");
		if(removeId != null){
			if(auth.isValidPizzaPrice(removeId)){	
				s.removePizza(Integer.parseInt(removeId));
				FlashMessage flashMessage = new FlashMessage();
				flashMessage.sendFlashMessage(request, "Pizza Removed", "msg");
				response.sendRedirect("browse");
				return;	
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/addpizza.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!isAllowed(request)){
			response.sendRedirect("browse"); 
			return;
		}
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		
		if(name == null || price == null || description == null){				
			request.setAttribute("error", "Could not create pizza!");
			RequestDispatcher view = request.getRequestDispatcher("views/pizza/addpizza.jsp");
			view.forward(request, response);
			return;
		}
		else if(!auth.isValidPizzaName(name) || !auth.isValidPizzaPrice(price) || !auth.isValidPizzaDescription(description)){
			request.setAttribute("error", "Could not create pizza, try again.");
			RequestDispatcher view = request.getRequestDispatcher("views/pizza/addpizza.jsp");
			view.forward(request, response);
			return;
		}
		try {
			s.addPizza(name, Integer.parseInt(price), description);
		} catch (Exception e) {
			request.setAttribute("error", "Could not add pizza to database, please try again.");
			RequestDispatcher view = request.getRequestDispatcher("views/pizza/addpizza.jsp");
			view.forward(request, response);
			return;
		}	
				
		request.setAttribute("msg", name + " pizza was added to inventory!");
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/addpizza.jsp");
		view.forward(request, response);
//		response.sendRedirect("browse");
	}
	
	private boolean isAllowed(HttpServletRequest request) throws IOException, ServletException{
		HttpSession s = request.getSession();
		User u = (User)s.getAttribute("user");
		if(u == null){ 
			return false;
		}
		else if (!auth.isAdmin(u)){
			return false;
		}
		return true;
	}
}
