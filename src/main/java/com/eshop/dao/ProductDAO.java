package com.eshop.dao;

import java.util.List;

import com.eshop.domain.Product;

public interface ProductDAO {
	public List<Product> list();
	public Product get(Integer id);
	public void add(Product product);
	public void update(Product product);
	public void delete(Integer id);
}
