<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<div style="text-align:center;">
<h1>La Pizze</h1>
<%
Class.forName("com.mysql.jdbc.Driver"); 
Connection con = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk/la_pizzeria", "la_pizzeria", "pizzatime"); 
Statement st= con.createStatement(); 
ResultSet rs=st.executeQuery("SELECT * FROM pizzas"); 

while(rs.next()) 
{ 
	%>
	
		<div style="margin-left: auto; margin-right: auto; width: 70%;border:1px solid black; display:block; text-align:left">
		Pizze:
	<%
		out.println(rs.getString("pizzaid")); 
		out.println(rs.getString("name")); 
		out.print(rs.getString("price"));	
	%>
		kr.<button>Add to basket</button></div>
		<%	
		} 
	%>
</div>
<br><br>

 
<sql:setDataSource var="pizz" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://mysql.itu.dk/la_pizzeria"
     user="la_pizzeria"  password="pizzatime"/>
<sql:query dataSource="${pizz}" var="result">
SELECT * from pizzas;
</sql:query>


 
<table border="1" width="100%">
<c:forEach var="row" items="${result.rows}">
<tr>
<td><c:out value="${row.pizzaid}"/></td>
<td><c:out value="${row.name}"/></td>
<td><c:out value="${row.price}"/></td>
</tr>
</c:forEach>
</table>

</body>
</html>