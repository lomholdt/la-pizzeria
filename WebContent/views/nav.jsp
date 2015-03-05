<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
     <div class="container">
       <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="${pageContext.request.contextPath}">La-Pizzeria</a>
       </div>
       <div id="navbar" class="collapse navbar-collapse">
         <ul class="nav navbar-nav">
           <li><a href="${pageContext.request.contextPath}/browse">Browse Pizz</a></li>
           <li><a href="${pageContext.request.contextPath}/createuser">Create User</a></li>
           <c:choose>
	           	<c:when test="${email ne null}">
		           <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
	           	</c:when>
	           	<c:otherwise>
	           		<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
	           	</c:otherwise>
           </c:choose>
         </ul>
       </div><!--/.nav-collapse -->
     </div>
</nav>