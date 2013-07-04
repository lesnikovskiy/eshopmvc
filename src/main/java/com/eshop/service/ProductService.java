package com.eshop.service;

import java.util.List;

import com.eshop.domain.Product;

public interface ProductService {
	public List<Product> list();
	public List<Product> list(int pageNumber, int pageSize);
	public int getTotalPages(int pageSize);
	public Product get(Integer id);
	public void add(Product product);
	public void update(Product product);
	public void delete(Integer id);
}
