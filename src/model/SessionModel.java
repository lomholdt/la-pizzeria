package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionModel {
	
	public SessionModel(HttpServletRequest r, String email) {
		HttpSession session = r.getSession();
		session.setAttribute("email", email);
		session.setMaxInactiveInterval(15*60);
	}
}
