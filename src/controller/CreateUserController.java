package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authenticator;
import model.SendMail;
import model.Statements;

/**
 * Servlet implementation class CreateUserController
 */
@WebServlet("/CreateUserController")
public class CreateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("views/register/create.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String phonenumber = request.getParameter("phonenumber");
		
		PrintWriter out = response.getWriter();
		
		Authenticator auth = new Authenticator();
		if(auth.isValidUser(email, password, name, address, zipcode, phonenumber)){
			Statements db = new Statements();
			if(db.checkEmail(email)) {
				if(db.addUserToDatabase(email, password, name, address, zipcode, phonenumber)){
					String pinCode = db.addPinToDatabase(email);
					SendMail mail = new SendMail();
					mail.sendPinCode(email, name, pinCode);	
					System.out.print("User valid");
					request.setAttribute("msg", "Please check your email to complete your registration");
					RequestDispatcher view = request.getRequestDispatcher("views/register/validate.jsp?mail="+email);
					view.forward(request, response);
				}
				else{
					System.out.print("Connection to the database failed");
					request.setAttribute("msg", "Connection to the database failed, please try again");
					RequestDispatcher view = request.getRequestDispatcher("views/register/create.jsp");
					view.forward(request, response);
				}
			}
			else {
				System.out.print("Email already exists in database");
				request.setAttribute("msg", "Could not create user: The email is already in use");
				RequestDispatcher view = request.getRequestDispatcher("views/register/create.jsp");
				view.forward(request, response);
			}
		}
		else{
			System.out.print("User information not vaild");
			request.setAttribute("msg", "Something went wrong. Are you sure you entered correct information?");
			RequestDispatcher view = request.getRequestDispatcher("views/register/create.jsp");
			view.forward(request, response);
		}
	}

}
