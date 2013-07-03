package com.eshop.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout") 
	public String logout() {
		return "login";
	}
	
	@RequestMapping("/fail")
	public String fail(Map<String, String> map) {
		map.put("error", "true");
		
		return "login";
	}
}
