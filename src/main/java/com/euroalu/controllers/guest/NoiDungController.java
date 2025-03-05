package com.euroalu.controllers.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.euroalu.models.Content;
import com.euroalu.services.ContentService;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class NoiDungController {
	@Autowired
	private ContentService contentService;
	

	@GetMapping("/us/{path}")
	public String showIntroductioPage(ModelMap model, HttpServletRequest request, @PathVariable("path") String path) throws Exception {
//		String path = request.getRequestURI(); 
		Content content =  contentService.getByPath(path);
		model.addAttribute("content", content);	
		return "user/content"; // Returns the HTML template name
	}
}