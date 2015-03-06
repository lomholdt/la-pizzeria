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
           <li><a href="${pageContext.request.contextPath}/browse">Browse Pizz</a></li>
		       <li><a href="${pageContext.request.contextPath}/basket">Basket <span class="badge">${basket.size}</span></a></li>
           <c:choose>
	           	<c:when test="${user ne null}">
		          	<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		          	<li><p class="navbar-text">Logged in as ${user.name}</p></li>
	           	</c:when>
	           	<c:otherwise>
	           		<li><a href="${pageContext.request.contextPath}/createuser">Create User</a></li>
	           		<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
	           	</c:otherwise>
           </c:choose>
           <c:if test="${user.role ne admin}"></c:if>
	           		<li><a href="${pageContext.request.contextPath}/addpizza">Add New Pizza</a></li>
         </ul>
       </div><!--/.nav-collapse -->
     </div>
</nav>