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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			if(db.addUserToDatabase(email, password, name, address, zipcode, phonenumber)){
				String pinCode = db.addPinToDatabase(email);
				SendMail mail = new SendMail();
				mail.sendPinCode(email, name, pinCode);	
			}
			out.print("user valid");
		}
		else{
			out.print("user not vaild");
		}
	}

}
