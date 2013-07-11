package com.eshop.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.eshop.domain.CartLine;
import com.eshop.domain.Paging;
import com.eshop.domain.Product;
import com.eshop.domain.ShoppingCart;
import com.eshop.service.PagingService;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/")
@SessionAttributes("shoppingCart")
public class HomeController {
	static final private int PAGE_SIZE = 2;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PagingService pagingService;
	
	@ModelAttribute("shoppingCart")
	public ShoppingCart initShoppingCart() {
		return new ShoppingCart();
	}
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/index/1";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "redirect:/index/1";
	}
	
	@RequestMapping(value = "/index/{pageNumber}", method = RequestMethod.GET)
	public String home(@PathVariable("pageNumber") Integer pageNumber, 
			Map<String, Object> map, 
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {	
		List<Product> products = productService.list(pageNumber, PAGE_SIZE); 
		Paging paging = pagingService.getPaging(pageNumber, PAGE_SIZE);
		
		map.put("products", products);
		map.put("paging", paging);
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
	
	@RequestMapping(value = "/increment", method = RequestMethod.POST)
	public String increment(@RequestParam("productName") String productName, 
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
		shoppingCart.increment(productName);
		
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/decrement", method = RequestMethod.POST)
	public String decrement(@RequestParam("productName") String productName, 
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
		shoppingCart.decrement(productName);
		
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeFromCart(@RequestParam("productName") String productName, 
			@ModelAttribute("shoppingCart") ShoppingCart shoppingCart) {
		CartLine line = shoppingCart.find(productName);
		if (line != null) 
			shoppingCart.remove(line);
		
		return "redirect:/cart";
	}
}
