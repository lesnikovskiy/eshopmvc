package com.eshop.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
	private List<CartLine> cartLines;

	public List<CartLine> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO, 2);
		List<CartLine> cartLines = getCartLines();
		
		if (cartLines != null) {
			for (CartLine line : cartLines) {
				totalPrice = totalPrice.add(line.getPrice());
			}
		}
		
		return totalPrice;
	}
	
	public void add(CartLine cartLine) {		
		List<CartLine> cartLines = getCartLines();
		if (cartLines == null)
			cartLines = new ArrayList<CartLine>();
		
		boolean found = false;
		for (CartLine line : cartLines) {
			if (line.getProduct().getName().equals(cartLine.getProduct().getName())) {
				found = true;
				line.setQuantity(line.getQuantity() + 1);
			}
		}
		
		if (!found)			
			cartLines.add(cartLine);
		
		setCartLines(cartLines);
	}
	
	public void remove(CartLine cartLine) {
		List<CartLine> cartLines = getCartLines();
		if (cartLines == null)
			return;
		
		Iterator<CartLine> iterator = cartLines.iterator();
		while(iterator.hasNext()) {
			CartLine line = iterator.next();
			if (line.getProduct().getName().equals(cartLine.getProduct().getName())) {
				iterator.remove();
			}
		}
		
		setCartLines(cartLines);
	}
	
	public void clear() {
		List<CartLine> cartLines = getCartLines();
		
		if (cartLines != null)
			cartLines.clear();
		
		setCartLines(cartLines);
	}
}
