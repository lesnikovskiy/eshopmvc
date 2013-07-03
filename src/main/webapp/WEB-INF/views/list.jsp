<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
	<style>
		.page { width: 713px; margin: 0 auto; font:14px Arial; color:#333; }
		.page table {border-collapse:collapse; margin:0; padding:0; width:100%; border:0; font:14px Arial; }
		.page table th, td { border-bottom:1px solid #333; padding:10px 25px; vertical-align: top; }
		.page .control { 
			color:#333; background:#EEE; border:1px solid #333; 
			cursor: pointer; border-radius:3px; 
			font:bold 12px Arial;
			display: block;
			float: left;
			width:80px;			
		}
		
		.page .control:nth-child(odd) {
			margin-right:3px;
		}
		
		.clear { clear:both; }
	</style>
</head>
<body>
	<div class="page">
		<h3>Product List</h3>
		
		<c:choose>
			<c:when test="${empty products}">
				<p>Product catalog is empty.</p>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>Image</th>
						<th>Name</th>
						<th>Price</th>
						<th>Category</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach var="product" items="${products}">
						<tr>
							<td>
								<c:choose>
									<c:when test="${empty product.file}">
										no image
									</c:when>
									<c:otherwise>
										<img src="${pageContext.request.contextPath}/download/${product.id}" width="100" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${product.name}</td>
							<td>$${product.price}</td>
							<td>${product.category.name}</td>
							<td>
								<form action="${pageContext.request.contextPath}/admin/edit/${product.id}">
									<input type="submit" value="edit" class="control" />
								</form>
								<form action="${pageContext.request.contextPath}/admin/remove" method="post">
									<input type="hidden" name="productId" value="${product.id}" />
									<input type="submit" value="delete" class="control" />
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		
		<div>
			<form action="${pageContext.request.contextPath}/admin/edit">
				<input type="submit" value="add product" class="control" />
			</form>
			<form action="<c:url value='/j_spring_security_logout' />">
				<input type="submit" value="logout" class="control" />
			</form>
		</div>
	</div>
</body>
</html>