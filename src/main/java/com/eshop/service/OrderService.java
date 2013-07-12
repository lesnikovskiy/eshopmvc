package com.eshop.service;

import java.util.List;

import com.eshop.domain.Order;

public interface OrderService {
	public List<Order> list();
	public Order get(int id);
	public void save(Order order);
	public void update(Order order);
	public void delete(int id);
}
