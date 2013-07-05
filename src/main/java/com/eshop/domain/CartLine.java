package com.eshop.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class CartLine {
	private Product product;
	private int quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		BigDecimal price = new BigDecimal(BigInteger.ZERO, 2);
		
		Product product = getProduct();
		
		if (product != null)
			price = product.getPrice().multiply(new BigDecimal(getQuantity()));
		
		return price;
	}
}
