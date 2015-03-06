package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Statements;

/**
 * Servlet implementation class ValidatePinController
 */
@WebServlet("/ValidatePinController")
public class ValidatePinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidatePinController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/register/validate.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pincode = request.getParameter("pincode");
		String mail = request.getParameter("mail");
		
		Statements s = new Statements();
		
		if(s.validateEmail(pincode, mail)) {
			s.removePinFromDatabase(mail);
			request.setAttribute("msg", "You are now registered, please login");
			RequestDispatcher view = request.getRequestDispatcher("views/login/login.jsp"); // TODO skal lige testes, ellers �ndr til stien til login.jsp
		}
		else {
			request.setAttribute("error", "Unable to validate PIN");
			request.setAttribute("mail", mail);
//			System.out.println(request.getRequestURI());
			RequestDispatcher view = request.getRequestDispatcher("views/register/validate.jsp");
			view.forward(request, response);
		}	
	}
}
