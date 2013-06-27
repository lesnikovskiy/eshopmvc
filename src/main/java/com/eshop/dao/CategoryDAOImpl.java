package com.eshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eshop.domain.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Category> list() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}
	
	public Category get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = null;
		try {
			category = (Category) session.createQuery("from Category where id=:id")
					.setInteger("id", id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return category;
	}
}
