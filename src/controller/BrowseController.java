package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.jdt.internal.compiler.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.FlashMessage;
import model.NameComparator;
import model.Pizza;
import model.PriceComparator;
import model.Statements;

/**
 * Servlet implementation class BrowseController
 */
@WebServlet("/BrowseController")
public class BrowseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			new FlashMessage().getFlashMessage(request, "msg"); // Flash message hack for servlets! :) 
			
			int page = 1;
			int pizzasPerPage = 10; 
			if(request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
			String sortOrder = sortBy(request);
			
			int offset = (page-1) * pizzasPerPage;
			
			Statements stmts = new Statements();
			
			String minPrice = request.getParameter("minPrice");
			String maxPrice = request.getParameter("maxPrice");
			List<Pizza> pizzas;
			int totalPizzas;
			
			if(minPrice != null && !minPrice.isEmpty() && maxPrice != null && !maxPrice.isEmpty()) {
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(minPrice);
				Matcher m1 = p.matcher(maxPrice);
				if (!m.matches() || !m1.matches()) {
					RequestDispatcher view = request.getRequestDispatcher("views/pizza/browse.jsp");
					request.setAttribute("error", "Minimum price or max price ERROR...<br>");
					view.forward(request, response);
					return;
				}
				
				pizzas = stmts.getPizzas(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
				pizzas.addAll(getXMLPizzas(Integer.parseInt(minPrice), Integer.parseInt(maxPrice)));
				if (sortOrder.equals("price")){
					Collections.sort(pizzas, new PriceComparator());
				}	
				else{
					Collections.sort(pizzas, new NameComparator());	
				}
				totalPizzas = pizzas.size();
				int toPizza = pizzasPerPage+offset > pizzas.size() ? pizzas.size() : pizzasPerPage+offset;
				pizzas = pizzas.subList(offset, toPizza);
			}
			else {
				pizzas = stmts.getPizzas();
				pizzas.addAll(getXMLPizzas(0, 0));

				if (sortOrder.equals("price")){
					Collections.sort(pizzas, new PriceComparator());
				}
				else{
					Collections.sort(pizzas, new NameComparator());	
				}
				totalPizzas = pizzas.size();
				int toPizza = pizzasPerPage+offset > pizzas.size() ? pizzas.size() : pizzasPerPage+offset;
				pizzas = pizzas.subList(offset, toPizza);
			}
			
			int totalPages = (int)Math.ceil(totalPizzas * 1.0 / pizzasPerPage);
			request.setAttribute("pizzas", pizzas);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("page", page);
			
		} catch (Exception e) {
			request.setAttribute("error", "Could not get pizzas...<br>" + e.getMessage());
		}
		
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/browse.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected String sortBy(HttpServletRequest request){
		String urlSort = request.getParameter("sortBy");
		
		if(urlSort == null){
			request.setAttribute("sortBy", "name");
			return "name";
		}
		else{
			request.setAttribute("sortBy", urlSort);
			return urlSort;
		}
	}
	
	protected List<Pizza> getXMLPizzas(int minPrice, int maxPrice){
		String url;
		if (minPrice == 0 && maxPrice == 0){
			url = "http://1-dot-lapizzagroup1-2.appspot.com/rest/pizza/list";
		}
		else{
			url = "http://1-dot-lapizzagroup1-2.appspot.com/rest/pizza/query?minPrice="+ minPrice + "&maxPrice=" + maxPrice;
		}
		
		List<Pizza> pizzas = new ArrayList<>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			doc.getDocumentElement ().normalize();
			
			NodeList listOfPizzas = doc.getElementsByTagName("pizzaList");
			
			for (int i = 0; i < listOfPizzas.getLength(); i++){
				Pizza p = new Pizza();
				Node pizza = listOfPizzas.item(i);
				
				if (pizza.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) pizza;
					
					String name = element.getElementsByTagName("Name").item(0).getTextContent();
					String price = element.getElementsByTagName("Price").item(0).getTextContent();
					Double price2 = Double.parseDouble(price);
					p.setId(Integer.MAX_VALUE-i);
					p.setName(name);
					p.setPrice(price2.intValue());
					
					NodeList listOfToppings = element.getElementsByTagName("pizzaToppings");
					String toppings = "";
					for(int j=0; j < listOfToppings.getLength(); j++){
						Node allToppings = listOfToppings.item(j);
						toppings += allToppings.getTextContent() + " ";
					}
					p.setDescription(toppings);
					
					pizzas.add(p);
				}	
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pizzas;
		
		
	}

}

