package com.euroalu.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.euroalu.models.Category;
import com.euroalu.models.Message;
import com.euroalu.models.Product;
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
		return "admin/categories/index2";
		//return "admin/categories/index2"; // Returns the HTML template name
	}
	@GetMapping("/{idCat}")
	public String getByIdCategory(ModelMap model, @PathVariable int idCat) throws Exception {
		Category categorys = categoryService.getCategoryById(idCat);
		System.out.println("CategoryManager "+ categorys);
		model.addAttribute("categories", categorys);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/categories/index";
		//return "admin/categories/index2"; // Returns the HTML template name
	}
	
	@GetMapping("/addP")
	public String getByIdCategory(ModelMap model) throws Exception {

		List<Category> categories = categoryService.getAllCategorys();
		model.addAttribute("categories", categories);
		model.addAttribute("product", new Product());
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/products/add";
		//return "admin/categories/index2"; // Returns the HTML template name
	}

}
