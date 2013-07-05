package com.eshop.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class PagingTests {
	@Test
	public void pagingReturnsNextPage() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(30);
		
		// act
		int next = paging.getNextPage();
		
		// assert
		Assert.assertEquals(next, 2);
	}
	
	@Test
	public void pagingReturnsFirstPageIfNextPageGreaterThanTotalCount() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(3);
		paging.setPageSize(10);
		paging.setRowsCount(30);
		
		// act
		int next = paging.getNextPage();
		
		// assert
		Assert.assertEquals(next, 1);
	}
	
	@Test
	public void pagingReturnsPreviousPage() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(2);
		paging.setPageSize(10);
		paging.setRowsCount(30);
		
		// act
		int prev = paging.getPrevPage();
		
		// assert
		Assert.assertEquals(prev, 1);
	}
	
	@Test
	public void pagingReturnsFirstPageIfPreviousPageIsZero() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(30);
		
		// act
		int prev = paging.getPrevPage();
		
		// assert
		Assert.assertEquals(prev, 1);
	}
	
	@Test
	public void pagingReturnsFirstPageIfCurrentPageIsNegative() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(-2);
		paging.setPageSize(10);
		paging.setRowsCount(30);
		
		// act
		int prev = paging.getPrevPage();
		
		// assert
		Assert.assertEquals(prev, 1);
	}
	
	@Test
	public void pagingReturnsCeilingTotalPages() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(33);
		
		// act
		int totalPages = paging.getTotalPages();
		
		// assert
		Assert.assertEquals(totalPages, 4);
	}
	
	@Test
	public void pagingReturnsArrayOfNumericPages() {
		// assert
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(33);
		
		// act
		List<String> pages = paging.getPages();
		
		// assert
		Assert.assertEquals(pages.size(), 4);
		Assert.assertEquals(pages.get(0), "1");
		Assert.assertEquals(pages.get(1), "2");
		Assert.assertEquals(pages.get(2), "3");
		Assert.assertEquals(pages.get(3), "4");
	}
}
