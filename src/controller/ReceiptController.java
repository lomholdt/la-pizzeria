package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Basket;
import model.FlashMessage;

/**
 * Servlet implementation class ReceiptController
 */
@WebServlet("/ReceiptController")
public class ReceiptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiptController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String allowCheckout = (String) request.getSession().getAttribute("allowCheckout");
		new FlashMessage().getFlashMessage(request, "allowCheckout"); 
		if(allowCheckout!= null && allowCheckout.equals("true")){
			request.getSession().removeAttribute("basket");
			RequestDispatcher view = request.getRequestDispatcher("views/checkout/receipt.jsp");
			view.forward(request, response);
		}
		else{
			response.sendRedirect("browse");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
