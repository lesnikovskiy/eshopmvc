package com.eshop.dao;

import java.util.List;

import com.eshop.domain.Category;

public interface CategoryDAO {
	public List<Category> list();
	public Category get(Integer id);
}
