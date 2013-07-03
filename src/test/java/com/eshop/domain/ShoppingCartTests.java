package com.eshop.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

public class ShoppingCartTests {
	@Test
	public void addCartLineTest() {
		// arrange
		ShoppingCart cart = new ShoppingCart();
		
		Product p = new Product();
		p.setId(1);
		
		CartLine line = new CartLine();	
		line.setQuantity(1);
		line.setProduct(p);
		
		// act
		cart.add(line);
		
		// assert
		Assert.assertEquals(line.getProduct().getId(), p.getId());
	}
	
	@Test
	public void getTotalPriceTest() {
		// arrange
		ShoppingCart cart = new ShoppingCart();
		
		Product p1 = new Product();
		p1.setName("p1");
		p1.setPrice(new BigDecimal(100));		
		Product p2 = new Product();
		p2.setName("p2");
		p2.setPrice(new BigDecimal(200));
		
		CartLine l1 = new CartLine();
		l1.setProduct(p1);
		l1.setQuantity(1);
		CartLine l2 = new CartLine();
		l2.setProduct(p2);
		l2.setQuantity(1);
		
		// act
		cart.add(l1);
		cart.add(l2);
		
		// assert
		Assert.assertEquals(cart.getTotalPrice(), convertToBigDecimal(300));
	}
	
	@Test
	public void addToCartMergesByProductNameTest() {
		// arrange
		Product p1 = new Product();
		p1.setName("p1");
		p1.setPrice(new BigDecimal(100));
		
		Product p2 = new Product();
		p2.setName("p1");
		p2.setPrice(new BigDecimal(100));
		
		CartLine l1 = new CartLine();
		l1.setProduct(p1);
		l1.setQuantity(2);
		
		CartLine l2 = new CartLine();
		l2.setProduct(p2);
		l2.setQuantity(1);
		
		ShoppingCart cart = new ShoppingCart();
		
		// act
		cart.add(l1);
		cart.add(l2);
		
		// assert
		Assert.assertEquals(cart.getTotalPrice(), convertToBigDecimal(300));
		Assert.assertEquals(cart.getCartLines().size(), 1);
	}
	
	@Test
	public void removeLineFromCartTest() {
		// arrange
		Product p1 = new Product();
		p1.setName("p1");
		p1.setPrice(new BigDecimal(500));
		
		Product p2 = new Product();
		p2.setName("p2");
		
		CartLine l1 = new CartLine();
		l1.setQuantity(1);
		l1.setProduct(p1);
		
		CartLine l2 = new CartLine();
		l2.setProduct(p2);
		l2.setQuantity(3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(l1);
		cart.add(l2);
		
		// act
		cart.remove(l2);
		
		// assert
		Assert.assertEquals(cart.getCartLines().size(), 1);
		Assert.assertEquals(cart.getCartLines().get(0).getProduct().getName(), "p1");
		Assert.assertEquals(cart.getCartLines().get(0).getQuantity(), 1);
		Assert.assertEquals(cart.getTotalPrice(), convertToBigDecimal(500));
	}
	
	@Test 
	public void cartLineClearTest() {
		// arrange
		Product p1 = new Product();
		p1.setName("p1");
		Product p2 = new Product();
		p2.setName("p2");
		Product p3 = new Product();
		p3.setName("p3");
		
		CartLine c1 = new CartLine();
		CartLine c2 = new CartLine();
		CartLine c3 = new CartLine();
		
		c1.setProduct(p1);
		c2.setProduct(p2);
		c3.setProduct(p3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(c1);
		cart.add(c2);
		cart.add(c3);
		
		// act
		cart.clear();
		
		// assert
		Assert.assertEquals(cart.getCartLines().size(), 0);
	}
	
	@Test
	public void findLineByProductNameTest() {
		// arrange
		Product p1 = new Product();
		p1.setName("Pro Spring 3");
		
		CartLine line = new CartLine();
		line.setProduct(p1);
		line.setQuantity(1);
		
		Product p2 = new Product();
		p2.setName("Pro ASP.NET MVC 4");
		
		CartLine line2 = new CartLine();
		line2.setProduct(p2);
		line2.setQuantity(3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(line);
		cart.add(line2);
		
		// act
		CartLine fline = cart.find("Pro ASP.NET MVC 4");
		
		// assert
		Assert.assertNotNull(fline);
		Assert.assertNotNull(fline.getProduct());
		Assert.assertEquals(fline.getProduct().getName(), "Pro ASP.NET MVC 4");
		Assert.assertEquals(fline.getQuantity(), 3);
	}
	
	@Test
	public void incrementLineIncreasesQuantityByOne() {
		// arrange
		Product p1 = new Product();
		p1.setName("Pro Spring 3");
		
		CartLine line = new CartLine();
		line.setProduct(p1);
		line.setQuantity(1);
		
		Product p2 = new Product();
		p2.setName("Pro ASP.NET MVC 4");
		
		CartLine line2 = new CartLine();
		line2.setProduct(p2);
		line2.setQuantity(3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(line);
		cart.add(line2);
		
		// act
		cart.increment("Pro Spring 3");
		
		// assert
		Assert.assertEquals(cart.getCartLines().get(0).getQuantity(), 2);
		Assert.assertEquals(cart.getCartLines().get(1).getQuantity(), 3);
	}
	
	@Test
	public void decrementLineDecreasesQuantityByOne() {
		// arrange
		Product p1 = new Product();
		p1.setName("Pro Spring 3");
		
		CartLine line = new CartLine();
		line.setProduct(p1);
		line.setQuantity(2);
		
		Product p2 = new Product();
		p2.setName("Pro ASP.NET MVC 4");
		
		CartLine line2 = new CartLine();
		line2.setProduct(p2);
		line2.setQuantity(3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(line);
		cart.add(line2);
		
		// act
		cart.decrement("Pro Spring 3");
		
		// assert
		Assert.assertEquals(cart.getCartLines().get(0).getQuantity(), 1);
		Assert.assertEquals(cart.getCartLines().get(1).getQuantity(), 3);
	}
	
	@Test
	public void decrementLineCannotSetZeroOrNegativeQuantity() {
		// arrange
		Product p1 = new Product();
		p1.setName("Pro Spring 3");
		
		CartLine line = new CartLine();
		line.setProduct(p1);
		line.setQuantity(1);
		
		Product p2 = new Product();
		p2.setName("Pro ASP.NET MVC 4");
		
		CartLine line2 = new CartLine();
		line2.setProduct(p2);
		line2.setQuantity(3);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(line);
		cart.add(line2);
		
		// act
		cart.decrement("Pro Spring 3");
		
		// assert
		Assert.assertEquals(cart.getCartLines().get(0).getQuantity(), 1);
		Assert.assertEquals(cart.getCartLines().get(1).getQuantity(), 3);
	}
	
	private BigDecimal convertToBigDecimal(double num) {
		return new BigDecimal(BigInteger.ZERO, 2).add(new BigDecimal(num));
	}
}
