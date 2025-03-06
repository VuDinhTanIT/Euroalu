package com.euroalu.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.euroalu.models.Category;
import com.euroalu.models.Content;
import com.euroalu.models.Message;
import com.euroalu.models.Product;
import com.euroalu.services.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = { "/admin/quan-ly-danh-muc" })
public class CategotyManagement {
	@Autowired
	private CategoryService categoryService;
	Message message;

	@GetMapping
	public String showCategoryList(ModelMap model) throws Exception {
		List<Category> categorys = categoryService.getAllCategorys();
		model.addAttribute("categories", categorys);
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/categories/index";
	}
	@GetMapping("/{idCat}")
	public String getByIdCategory(ModelMap model, @PathVariable int idCat) throws Exception {
		Category category = categoryService.getCategoryById(idCat);
		List<Category> categories = categoryService.getAllCategorys();

//		System.out.println("CategoryManager "+ categorys);
		model.addAttribute("categories", categories);
		model.addAttribute("category", category);
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/categories/edit";
	}
	
	@GetMapping("/them")
	public String getByIdCategory(ModelMap model) throws Exception {

		List<Category> categories = categoryService.getAllCategorys();
		model.addAttribute("categories", categories);
		model.addAttribute("newCategory", new Category());
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/categories/add";
	}
	@PostMapping
	public String postContent(@ModelAttribute("newCategory") Category category) {
		Category categoryCreated = categoryService.post(category);
		
		if (categoryCreated == null) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-danh-muc/them"; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-danh-muc";
	}
	
	@PostMapping("/{id}")
	public String updateContent(@PathVariable("id") Long id, @ModelAttribute("category") Category category) {
		Category categoryupdated = categoryService.post(category);
		
		if (categoryupdated == null) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-danh-muc/" + id; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-danh-muc";
	}


}
