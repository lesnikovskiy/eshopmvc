package com.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.ProductDAO;
import com.eshop.domain.Paging;

@Service
public class PagingServiceImpl implements PagingService {
	@Autowired
	private ProductDAO productDAO;
	
	@Transactional
	public Paging getPaging(int pageNumber, int pageSize) {
		int totalPages = productDAO.getTotalRowsCount();
		
		Paging paging = new Paging();
		paging.setRowsCount(totalPages);
		paging.setPageNumber(pageNumber);
		paging.setPageSize(pageSize);		
		
		return paging;
	}
}
