package com.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.ProductDAO;
import com.eshop.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;
	
	@Transactional
	public List<Product> list() {
		return productDAO.list();
	}
	
	@Transactional
	public List<Product> list(int pageNumber, int pageSize) {
		return productDAO.list(pageNumber, pageSize);
	}

	@Transactional
	public Product get(Integer id) {
		return productDAO.get(id);
	}

	@Transactional
	public void add(Product product) {
		productDAO.add(product);		
	}

	@Transactional
	public void update(Product product) {
		productDAO.update(product);
	}

	@Transactional
	public void delete(Integer id) {
		productDAO.delete(id);
	}
}
