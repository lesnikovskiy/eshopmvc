package com.eshop.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="orderlines")
public class OrderLine {
	@ManyToOne(targetEntity = Order.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinTable(
			name="orders", 
			joinColumns={@JoinColumn(table="orders", name="id", referencedColumnName="FK_orders_orders")}, 
			inverseJoinColumns={@JoinColumn(table="orderlines", name="FK_orders_orders", referencedColumnName="order_id")})
	private Order order;
	
	@ManyToOne(targetEntity = Product.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinTable(
			name="products",
			joinColumns=@JoinColumn(name="product_id")
			)
	private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="product_price")
	private BigDecimal productPrice;
	
	@Column(name="total_price")
	private BigDecimal totalPrice;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
