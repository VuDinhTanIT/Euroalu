package com.euroalu.controllers.guest;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping(value = {"/"})
public class Index {



	
	@GetMapping
	public String showGuestList(ModelMap model, HttpSession session) throws Exception {
		
		return "index"; // Returns the HTML template name
	}
	@GetMapping("old")
	public String showGuestListd(ModelMap model, HttpSession session) throws Exception {
		
		return "index_old"; // Returns the HTML template name
	}
	@GetMapping("gioi-thieu")
	public String showGuestListd1(ModelMap model, HttpSession session) throws Exception {
		
		
		return "index_old"; // Returns the HTML template name
	}
}