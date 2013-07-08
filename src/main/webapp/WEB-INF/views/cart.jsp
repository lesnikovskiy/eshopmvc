<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
<style>
	.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
	.header {float: left; display: block; padding:0; margin: 0;}
	.aside {float:right; margin:0; padding:0;}
	.aside a.checkout { font:12px Arial; color:#333; text-decoration: underline; }
	.entry { border-bottom:1px solid #333; padding:0; margin:5px 0; } 
	.page .descr {float:left; }
	.page .descr .product {width:150px;}
	.page .qty { float: right; margin:0; padding: 0; }
	.page .qty p { background:#EEE; border-radius: 5px; padding: 4px; font:bold 12px Arial; }
	.page .buttons { }
	.page .buttons .cart-button { 
		color:#333; background:#EEE; border:1px solid #333; 
		cursor: pointer; border-radius:3px; 
		font:bold 12px Arial;
		display: block; float: right;
		width:150px;
	}
	.page .buttons .control {
		float:left;
		margin-right:3px;
		margin-top:10px;
		margin-left:25px;
	}
	.page .buttons .control-button { 
		color:#333; background:#EEE; border:1px solid #333; 
		cursor: pointer; border-radius:3px; 
		font:bold 12px Arial;
		display: block; float: left;
		width:30px;
	}
	.page .buttons input[type=submit].cart-button:nth-child(odd) {margin-right:3px;}
	.clear { clear:both; }
</style>
</head>
<body>
<div class="page">
		<div class="header">
			<h1>Selected items:</h1>
		</div>		
		<div class="clear"></div>
		<c:choose>
			<c:when test="${empty shoppingCart.cartLines}">
				<p>Shopping cart is empty</p>
			</c:when>
			<c:otherwise>
				<div class="entry">
					<div class="descr">
						<div class="product">
							<p><strong>Name</strong></p>
						</div>
					</div>
					<div class="descr"><p><strong>Price</strong></p></div>
					<div class="qty"><p><strong>Quantity</strong></p></div>
					<div class="clear"></div>
				</div>
				
				<c:forEach items="${shoppingCart.cartLines}" var="line">
					<div class="entry">
						<div class="descr">
							<div class="product">
								<p>${line.product.name}</p>
							</div>							
						</div>
						<div class="descr">
							<p>$${line.price}</p>
						</div>
						<div class="descr">
							<div class="buttons">
								<div class="control">
									<form method="post" action="${pageContext.request.contextPath}/increment">
										<input type="hidden" name="productName" value="${line.product.name}" />
										<input type="submit" value="+" class="control-button" />
									</form>
								</div>
								<div class="control">
									<form method="post" action="${pageContext.request.contextPath}/decrement">
										<input type="hidden" name="productName" value="${line.product.name}" />
										<input type="submit" value="-" class="control-button" />
									</form>
								</div>
								<div class="control">
									<form method="post" action="${pageContext.request.contextPath}/removeFromCart">
										<input type="hidden" name="productName" value="${line.product.name}" />
										<input type="submit" value="remove from cart" class="cart-button" />
									</form>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="qty">
							<p>${line.quantity}</p>
						</div>
						<div class="clear"></div>
					</div>
				</c:forEach>		
				<div class="entry">
					<div class="descr"><p><strong>Total:</strong></p></div>
					<div class="descr"><p><strong>$${shoppingCart.totalPrice}</strong></p></div>
					<div class="qty"><p></p></div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</c:otherwise>
		</c:choose>
		
		<div class="buttons">
			<form action="${pageContext.request.contextPath}/index">
				<input type="submit" value="continue shopping" class="cart-button" />
			</form>
			<form action="${pageContext.request.contextPath}/checkout">
				<input type="submit" value="checkout" class="cart-button" />
			</form>
		</div>
	</div>
</body>
</html>