package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Basket;
import model.FlashMessage;
import model.Pizza;

/**
 * Servlet implementation class CreateCustomPizza
 */
@WebServlet("/CreateCustomPizza")
public class CreateCustomPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomPizza() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/views/pizza/custom-pizza.jsp");
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> m  = request.getParameterMap();
		
		Pizza customPizza = new Pizza();
		String customTopping = "";
		int customPrice = 50;
		for(Map.Entry<String, String[]> entry : m.entrySet()){
			if (entry.getKey().equals("name")){
				if (entry.getValue()[0].isEmpty()){
					request.setAttribute("error", "You must specify a name for you custom pizza.");
					RequestDispatcher view = request.getRequestDispatcher("/views/pizza/custom-pizza.jsp");
					view.forward(request, response);
					return;
				}
				customPizza.setName(entry.getValue()[0]);
			}
			else if(entry.getKey().equals("topping")){
				for (String topping : entry.getValue()) {
					if(!topping.equals("")){						
						customTopping += topping + ", ";
						customPrice += 15;
					}
				}
			}
		}
		customPizza.setId(customPizza.hashCode()); // This is not a scalable solution!
		if(customTopping.length() < 3){
			customPizza.setDescription("A no topping pizza");
		}else{
			customPizza.setDescription(customTopping.substring(0, customTopping.length() - 2));			
		}
		customPizza.setPrice(customPrice);
		
		HttpSession session = request.getSession();
		Basket basket = (Basket) session.getAttribute("basket");
		if(basket == null){
			basket = new Basket();
			session.setAttribute("basket", basket);
		}
		basket.add(customPizza);
		
		FlashMessage fm = new FlashMessage();
		fm.sendFlashMessage(request, "Nice one! We have added your custom pizza to the basket", "msg");
		response.sendRedirect("basket");
		
	}
	
	protected void returnWithError(HttpServletRequest request, HttpServletResponse response){
		
	}

}
