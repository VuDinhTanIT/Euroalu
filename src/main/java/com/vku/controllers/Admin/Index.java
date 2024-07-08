package com.vku.controllers.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Index {

	@GetMapping("/index")
	public String index(ModelMap modelMap) {
		
		return "index";
		
	}
	@GetMapping("/p")
	public String p(ModelMap modelMap) {
		
		return "admin/positions/index";
		
	}
}
