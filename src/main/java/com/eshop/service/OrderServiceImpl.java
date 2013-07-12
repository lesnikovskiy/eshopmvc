package com.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.OrderDAO;
import com.eshop.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional
	public List<Order> list() {
		return orderDAO.list();
	}

	@Transactional
	public Order get(int id) {
		return orderDAO.get(id);
	}

	@Transactional
	public void save(Order order) {
		orderDAO.save(order);
	}

	@Transactional
	public void update(Order order) {
		orderDAO.update(order);
	}

	@Transactional
	public void delete(int id) {
		orderDAO.delete(id);
	}
}
