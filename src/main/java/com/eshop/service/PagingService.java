package com.eshop.service;

import com.eshop.domain.Paging;

public interface PagingService {
	public Paging getPaging(int pageNumber, int pageSize);
}
