package com.eshop.domain;

import java.util.ArrayList;
import java.util.List;

public final class Paging {
	private int pageSize;	
	private int pageNumber;
	private int rowsCount;
	
	private int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	private int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPrevPage() {
		int prev = getPageNumber() - 1;
		
		return prev <= 0 ? 1 : prev;
	}
	public int getNextPage() {
		int next = getPageNumber() + 1;
		
		return next > getTotalPages() ? 1 : next;
	}
	public int getTotalPages() {
		int totalPages = (getRowsCount() - 1) / getPageSize() + 1;
		
		return totalPages;
	}
	private int getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public List<String> getPages() {
		List<String> pages = new ArrayList<String>();
		
		for (int i = 0; i < getTotalPages(); i++) {
			pages.add(String.valueOf(i + 1));
		}
		
		return pages;
	}
}
