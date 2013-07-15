package com.eshop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="orderlines")
public class OrderLine {
	@ManyToOne(targetEntity = Order.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			joinColumns = {@JoinColumn(table="")})
	@JoinColumn(name="order_id")
	private Order order;
}
