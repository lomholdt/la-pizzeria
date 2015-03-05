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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getBasket(request);
		String itemId = request.getParameter("addToBasket");
		System.out.println("itemId: " + itemId);
		if (itemId != null && !itemId.isEmpty()){
			int id = Integer.parseInt(itemId);
			System.out.println("itemId is not null");
			try {
				Statements stmts = new Statements();
				System.out.println("Created Statements object");
				Item item = stmts.getPizza(id); // fails if id is invalid
				System.out.println("Got pizza from database...");
				basket.add(item);
				System.out.println("Adding pizza to basket...");
			} catch (Exception e) {
				request.setAttribute("error", "Sorry, no Pizzas are available with id " + itemId);
				e.getStackTrace();
			}
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
	
	protected Basket getBasket(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("basket") == null){
			System.out.println("Session basket is null, creating new basket...");
			basket = new Basket();
			session.setAttribute("basket", basket);
		}
		return basket;
	}
}
