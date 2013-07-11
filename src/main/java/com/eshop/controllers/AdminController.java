package com.eshop.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.domain.Category;
import com.eshop.domain.Paging;
import com.eshop.domain.Product;
import com.eshop.service.CategoryService;
import com.eshop.service.PagingService;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final int PAGE_SIZE = 2;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PagingService pagingService;
	
	@RequestMapping("/list")
	public String list() {			
		return "redirect:/admin/list/1";			
	}
	
	@RequestMapping("/list/{pageNumber}")
	public String paginationList(@PathVariable("pageNumber") Integer pageNumber, 
			Map<String, Object> map, Principal principal) {		
		List<Product> products = productService.list(pageNumber, PAGE_SIZE);
		Paging paging = pagingService.getPaging(pageNumber, PAGE_SIZE);
		
		map.put("products", products);
		map.put("principal", principal);
		map.put("paging", paging);
		
		return "list";
	}
	
	@RequestMapping("/edit/{pageNumber}")
	public String edit(@PathVariable("pageNumber") Integer pageNumber, Map<String, Object> map) {	
		map.put("product", new Product());
		map.put("categories", categoryService.list());
		map.put("action", "save");
		map.put("pageNumber", pageNumber);
		
		
		return "edit";
	}
	
	@RequestMapping("/edit/{productId}/{pageNumber}")
	public String editProduct(@PathVariable("productId") Integer productId, 
			@PathVariable("pageNumber") Integer pageNumber,
			Map<String, Object> map) {
		Product product = productService.get(productId);
		List<Category> categories = categoryService.list();
		
		if (product != null) {
			for (Category c : categories)
				if (product.getCategory().getShortName().equals(c.getShortName()))
					c.setSelected(true);
		}
		
		map.put("product", product);
		map.put("categories", categories);
		map.put("action", "update");
		map.put("pageNumber", pageNumber);
		
		return "edit";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, BindingResult result, 
			@RequestParam("file") MultipartFile file, 
			@RequestParam("category") Integer categoryId,
			@RequestParam("pageNumber") Integer pageNumber) {		
		Product p = initProduct(product, file, categoryId);
		
		try {
			productService.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/list/" + pageNumber;
	}	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product, BindingResult result,
			@RequestParam("file") MultipartFile file, 
			@RequestParam("category") Integer categoryId,
			@RequestParam("pageNumber") Integer pageNumber) {
		Product p = initProduct(product, file, categoryId);
		
		try {
			productService.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/list/" + pageNumber;
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("productId") Integer productId, @RequestParam("pageNumber") Integer pageNumber) {
		productService.delete(productId);
		
		return "redirect:/admin/list/" + pageNumber;
	}
	
	private Product initProduct(Product product, MultipartFile file, Integer categoryId) {
		Category category = categoryService.get(categoryId);
		if (category != null)
			product.setCategory(category);
		
		try {
			if (file != null && file.getInputStream().available() > 0) {
				Blob fileBlob = Hibernate.createBlob(file.getInputStream());
				
				product.setMime(file.getContentType());
				product.setFile(fileBlob);
			} else {
				if (product.getId() != null && product.getId() > 0) {
					Product p = productService.get(product.getId());
					if (p != null) {
						product.setMime(p.getMime());
						product.setFile(p.getFile());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return product;
	}
}
