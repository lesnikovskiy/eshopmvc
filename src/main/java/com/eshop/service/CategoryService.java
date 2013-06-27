package com.eshop.service;

import java.util.List;

import com.eshop.domain.Category;

public interface CategoryService {
	public List<Category> list();
	public Category get(Integer id);
}
