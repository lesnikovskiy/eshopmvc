<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
	<style>
		.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
		.header {float: left; display: block; padding:0; margin: 0;}
		.aside {float:right; margin:0; padding:0;}
		.aside a.checkout { font:12px Arial; color:#333; text-decoration: underline; }
		.aside p.cart-summary { font:12px Arial; color:#333; }
		.entry { border-bottom:1px solid #333; padding:0; margin:5px 0; } 
		.page .descr {float:left; width:300px;}
		.page .buttons { float: right; margin:0; padding: 0; }
		.page .buttons .cart-button { 
			color:#333; background:#EEE; border:1px solid #333; 
			cursor: pointer; border-radius:3px; 
			font:bold 12px Arial;
			display: block; float: right;
		}
		.clear { clear:both; }
	</style>
</head>
<body>
	<div class="page">
		<div class="header">
			<h3>Products</h3>
		</div>
		<div class="aside">
			<p class="cart-summary">
				<c:choose>
					<c:when test="${empty shoppingCart.cartLines}">
						Shopping cart is empty.
					</c:when>
					<c:otherwise>
						You've got <b>${shoppingCart.cartLines.size()}</b> item(s) in your 
							<a href="${pageContext.request.contextPath}/cart" class="checkout">shopping cart</a>
					</c:otherwise>
				</c:choose>			
			</p>
		</div>
		<div class="clear"></div>
		
		<c:choose>
			<c:when test="${empty products}">
				<p>Product catalog is empty.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="product" items="${products}">
					<div class="entry">
						<div class="descr">	
							<p>
								<c:choose>
									<c:when test="${empty product.file}">
										no image preview
									</c:when>
									<c:otherwise>
										<img src="${pageContext.request.contextPath}/download/${product.id}" width="250" />
									</c:otherwise>
								</c:choose>						
							</p>					
							<p>${product.name}</p>
							<p>Price: $${product.price}</p>
							<p><a href="${pageContext.request.contextPath}/edit/${product.id}">[edit]</a></p>
						</div>
						<div class="buttons">
							<div>
								<form method="post" action="${pageContext.request.contextPath}/addtocart">
									<input type="hidden" name="productid" value="${product.id}" />
									<input type="submit" value="add to cart" class="cart-button" />
								</form>
							</div>
						</div>	
						<div class="clear"></div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<div>
			<a href="${pageContext.request.contextPath}/edit">add new product</a>
		</div>
	</div>
</body>
</html>
