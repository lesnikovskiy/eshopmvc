package com.eshop.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eshop.domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Product> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> products = null;
		try {
//			products = (List<Product>) session.createQuery("from Product where isdeleted!=:isdeleted")
//					.setBoolean("isdeleted", true).list();
			products = (List<Product>) session.createQuery("from Product").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	public Product get(Integer id) {		
		Session session = sessionFactory.getCurrentSession();
		
		Product product = null;
		try {
			product = (Product) session.createQuery("from Product where id=:id")
					.setInteger("id", id).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	public void add(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	public void update(Product product) {
		sessionFactory.getCurrentSession().update(product);		
	}

	public void delete(Integer id) {
		Product product = get(id);
		if (product != null) {
			product.setDeleted(true);
			sessionFactory.getCurrentSession().update(product);
		}
	}

}
