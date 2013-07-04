package com.eshop.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

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
import com.eshop.domain.Product;
import com.eshop.service.CategoryService;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/list")
	public String list(Map<String, Object> map, Principal principal) {
		map.put("products", productService.list());
		map.put("principal", principal);
		
		return "list";			
	}
	
	@RequestMapping("/list/{pageNumber}")
	public String paginationList(@PathVariable("pageNumber") Integer pageNumber, 
			Map<String, Object> map, Principal principal) {		
		List<Product> products = productService.list(pageNumber, 2);
		
		map.put("products", products);
		map.put("principal", principal);
		map.put("prev", pageNumber - 1 <= 0 ? 1 : pageNumber - 1);
		map.put("next", pageNumber + 1);
		
		return "list";
	}
	
	@RequestMapping("/edit")
	public String edit(Map<String, Object> map) {
		map.put("product", new Product());
		map.put("categories", categoryService.list());
		map.put("action", "save");
		
		return "edit";
	}
	
	@RequestMapping("/edit/{productId}")
	public String editProduct(@PathVariable("productId") Integer productId, 
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
		
		return "edit";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, BindingResult result, 
			@RequestParam("file") MultipartFile file, @RequestParam("category") Integer categoryId) {		
		Product p = initProduct(product, file, categoryId);
		
		try {
			productService.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/list";
	}	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product, BindingResult result,
			@RequestParam("file") MultipartFile file, @RequestParam("category") Integer categoryId) {
		Product p = initProduct(product, file, categoryId);
		
		try {
			productService.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/list";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("productId") Integer productId) {
		productService.delete(productId);
		
		return "redirect:/admin/list";
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
