package com.euroalu.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.euroalu.models.Category;
import com.euroalu.models.Message;
import com.euroalu.services.CategoryService;

@Controller
@RequestMapping(value = { "/admin/quan-ly-danh-muc" })
public class CategotyManagement {
	@Autowired
	private CategoryService categoryService;
	Message message;

	@GetMapping
	public String showCategoryList(ModelMap model) throws Exception {
		List<Category> categorys = categoryService.getAllCategorys();
//		System.out.println("CategoryManager "+ categorys);
		model.addAttribute("categories", categorys);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/categories/index2"; // Returns the HTML template name
	}
}
