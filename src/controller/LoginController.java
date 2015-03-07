package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FlashMessage;
import model.SessionModel;
import model.Statements;
import model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/login/login.jsp");
		FlashMessage message = new FlashMessage();
		message.getFlashMessage(request, "msgOK");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statements s = new Statements();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(s.login(email, password)) {
			User currentUser = s.getUser(email);
			new SessionModel(request, currentUser);
			response.sendRedirect("browse");
		}
		else {
			request.setAttribute("msg", "Email or password was incorrect");
			RequestDispatcher view = request.getRequestDispatcher("views/login/login.jsp");
			view.forward(request, response);
		}
	}

}
