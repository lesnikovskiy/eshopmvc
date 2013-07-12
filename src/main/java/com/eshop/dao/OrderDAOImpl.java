package com.eshop.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eshop.domain.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> list() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Order where isdeleted!=:isdeleted")
				.setBoolean("isdeleted", true)
				.list();
	}

	@Override
	public Order get(int id) {
		return (Order) sessionFactory.getCurrentSession()
				.createQuery("from Order where id=:id")
				.setInteger("id", id)
				.uniqueResult();
	}

	@Override
	public void save(Order order) {
		sessionFactory.getCurrentSession().save(order);		
	}

	@Override
	public void update(Order order) {
		sessionFactory.getCurrentSession().update(order);
	}

	@Override
	public void delete(int id) {
		Order order = this.get(id);
		if (order != null) {
			order.setIsdeleted(true);
			this.update(order);
		}
	}	
	
}
