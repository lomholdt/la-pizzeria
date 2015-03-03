package model;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import sun.rmi.server.Activation;

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
            message.setFrom(new InternetAddress("noReply@laPizzeria.it"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Welcome to La Pizzeria - please verify your account");
            message.setText("Dear "+ name
                + "\n\n Please verify your account by using the pincode below."
                + "\n pincode: " + pincode + "\n use the following link: <a href='localhost:8080/la-pizzeria/validate?email="+email+"'</a>");
            
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
	    }
	}
}