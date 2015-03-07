<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-fixed-top">
     <div class="container">
       <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="${pageContext.request.contextPath}"><img class="logo" alt="Brand" src="${pageContext.request.contextPath}/img/logo.png"></a>
         
       </div>
       <div id="navbar" class="collapse navbar-collapse">
         <ul class="nav navbar-nav">
           <li><a href="${pageContext.request.contextPath}/browse">Pizzas</a></li>
		       <li><a href="${pageContext.request.contextPath}/basket">Basket 
		       <span class="badge">
		       		<c:choose>
		       			<c:when test="${basket eq null}">
		       			0
		       			</c:when>
		       		<c:otherwise>
		       			${basket.size}
		       		</c:otherwise>
		       		</c:choose>
		       </span></a></li>
           <c:choose>
	           	<c:when test="${user ne null}">
		          	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		          	<li><p class="navbar-text">Logged in as <a href="#">${user.name}</a></p></li>
	           	</c:when>
	           	<c:otherwise>
	           		<li><a href="${pageContext.request.contextPath}/createuser">Create user</a></li>
	           		<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
	           	</c:otherwise>
           </c:choose>
           <c:if test="${user.role eq 'admin'}">
	           		<li><a href="${pageContext.request.contextPath}/addpizza">Add new pizza</a></li>
	       </c:if>
         </ul>
       </div><!--/.nav-collapse -->
     </div>
</nav>