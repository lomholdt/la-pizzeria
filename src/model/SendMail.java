package model;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendMail{
	
   public void sendPinCode(String email, String name, String pincode){
		final String username = "lapizzeriacph@gmail.com";
	    final String password = "pizzatime";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@lapizzeria.it"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Welcome to La Pizzeria - please verify your account");
            message.setContent("Dear "+ name +", "
                + "<br>Please verify your account by using the pincode below."
                + "<br>Insert your pincode, <b>" + pincode + "</b>, in the following link: <a href=\"http://localhost:8080/la-pizzeria/validate?mail="+email+"\">Click here</a>", "text/html; charset=utf-8");
            
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
	    }
	}
}