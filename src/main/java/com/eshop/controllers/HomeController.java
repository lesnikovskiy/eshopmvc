package com.eshop.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.domain.CartLine;
import com.eshop.domain.Category;
import com.eshop.domain.Product;
import com.eshop.domain.ShoppingCart;
import com.eshop.service.CategoryService;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/")
@SessionAttributes("shoppingCart")
public class HomeController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("shoppingCart")
	public ShoppingCart initShoppingCart() {
		return new ShoppingCart();
	}
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Map<String, Object> map, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {			
		map.put("products", productService.list());
		map.put("shoppingCart",  shoppingCart != null ? shoppingCart : initShoppingCart());
		
		return "home";
	}	
	
	@RequestMapping(value="/download/{productId}")
	public String downloadFile(@PathVariable("productId") Integer productId, HttpServletResponse response) {
		Product product = productService.get(productId);
		
		if (product != null) {
			try {
				OutputStream outputStream = response.getOutputStream();
				response.setContentType(product.getMime());
				
				IOUtils.copy(product.getFile().getBinaryStream(), outputStream);
				
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
//	@RequestMapping("/edit")
//	public String edit(Map<String, Object> map) {
//		map.put("product", new Product());
//		map.put("categories", categoryService.list());
//		map.put("action", "save");
//		
//		return "edit";
//	}
	
//	@RequestMapping("/edit/{productId}")
//	public String editProduct(@PathVariable("productId") Integer productId, 
//			Map<String, Object> map) {
//		Product product = productService.get(productId);
//		List<Category> categories = categoryService.list();
//		
//		if (product != null) {
//			for (Category c : categories) {
//				if (product.getCategory().getShortName().equals(c.getShortName()))
//					c.setSelected(true);
//			}
//		}
//		
//		map.put("product", product);
//		map.put("categories", categories);
//		map.put("action", "update");
//		
//		return "edit";
//	}
	
//	@RequestMapping(value="/save", method = RequestMethod.POST)
//	public String save(@ModelAttribute("product") Product product, BindingResult result, 
//			@RequestParam("file") MultipartFile file, @RequestParam("category") Integer categoryId) {		
//		Product p = initProduct(product, file, categoryId);
//		
//		try {
//			productService.add(p);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "redirect:/index";
//	}	
//	
//	@RequestMapping(value="update", method = RequestMethod.POST)
//	public String update(@ModelAttribute("product") Product product, BindingResult result,
//			@RequestParam("file") MultipartFile file, @RequestParam("category") Integer categoryId) {
//		Product p = initProduct(product, file, categoryId);
//		
//		try {
//			productService.update(p);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "redirect:/index";
//	}
	
	@RequestMapping(value="/addtocart", method=RequestMethod.POST)
	public String addToCart(@RequestParam("productid") Integer productId, 
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {		
		Product product = productService.get(productId);
		if (product != null) {
			CartLine line = new CartLine();
			line.setProduct(product);
			line.setQuantity(1);
			shoppingCart.add(line);
		}
		
		return "redirect:/index";
	}
	
	@RequestMapping("/cart")
	public String cart(Map<String, Object> map, @ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
		map.put("shoppingCart", shoppingCart);
		
		return "cart";
	}
	
//	private Product initProduct(Product product, MultipartFile file, Integer categoryId) {
//		Category category = categoryService.get(categoryId);
//		if (category != null)
//			product.setCategory(category);
//		
//		try {
//			if (file != null && file.getInputStream().available() > 0) {
//				Blob fileBlob = Hibernate.createBlob(file.getInputStream());
//				
//				product.setMime(file.getContentType());
//				product.setFile(fileBlob);
//			} else {
//				if (product.getId() > 0) {
//					Product p = productService.get(product.getId());
//					if (p != null) {
//						product.setMime(p.getMime());
//						product.setFile(p.getFile());
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return product;
//	}
}
