package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionModel {
	
	public SessionModel(HttpServletRequest r, User user) {
		HttpSession session = r.getSession();
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(15*60);
	}
	
}
