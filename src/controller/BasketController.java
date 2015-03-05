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
import javax.servlet.http.HttpSession;

import model.Basket;
import model.Item;
import model.Statements;

/**
 * Servlet implementation class BasketController
 */
@WebServlet("/BasketController")
public class BasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Basket basket;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getBasket(request);
		String addId = request.getParameter("add");
		String removeId = request.getParameter("remove");
		
		if(addId != null && !addId.isEmpty()){
			if(!addToBasket(addId)) request.setAttribute("error", "Sorry, no Pizzas are available with id " + addId);
		}
		else if (removeId != null && !removeId.isEmpty()){
			if(!removeFromBasket(removeId)) request.setAttribute("error", "Could not remove item " + removeId + " from basket.");
		}
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("views/basket/basket.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected boolean addToBasket(String itemId){
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(itemId);
		if(m.matches()){
			int id = Integer.parseInt(itemId);
			try {
				Statements stmts = new Statements();
				Item item = stmts.getPizza(id); // fails if id is invalid
				basket.add(item);
				return true;
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	return false;
	}
	
	protected boolean removeFromBasket(String removeId){
		Pattern p = Pattern.compile("^\\d+$");
		Matcher m = p.matcher(removeId);
		if(m.matches()){
			int id = Integer.parseInt(removeId);
			try {
				if(id < basket.getSize()) basket.remove(id); return true;
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return false;
	}
	
	protected Basket getBasket(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("basket") == null){
			basket = new Basket();
			session.setAttribute("basket", basket);
		}
		return basket;
	}
}
