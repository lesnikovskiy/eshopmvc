package com.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.CategoryDAO;
import com.eshop.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Transactional
	public List<Category> list() {
		return categoryDAO.list();
	}
	
	@Transactional
	public Category get(Integer id) {
		return categoryDAO.get(id);
	}
}
