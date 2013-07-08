package com.eshop.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class PagingTests {
	@Test
	public void getNextPage_returns_the_next_page() {
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
	public void getNextPage_returns_1st_page_if_next_page_greater_than_totalPages() {
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
	public void getPrevPage_returns_previous_page() {
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
	public void getPrevPage_returns_first_page_if_previous_page_is_zero() {
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
	public void getPrevPage_returns_first_page_if_current_page_is_negative() {
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
	public void getTotalPages_returns_ceiling_total_page_numbers() {
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
	public void getPages_returns_array_of_page_numbers() {
		// arrange
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
	
	@Test
	public void pagerVisible_returns_true_if_totalPages_greater_than_1() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(33);
		
		// act
		boolean pagerVisible = paging.isPagerVisible();
		
		// assert
		Assert.assertEquals(pagerVisible, true);
	}
	
	@Test
	public void pagerVisible_returns_false_if_totalPages_less_than_1() {
		// arrange
		Paging paging = new Paging();
		paging.setPageNumber(1);
		paging.setPageSize(10);
		paging.setRowsCount(8);
		
		// act
		boolean pagerVisible = paging.isPagerVisible();
		
		// assert
		Assert.assertEquals(pagerVisible, false);
	}
}
