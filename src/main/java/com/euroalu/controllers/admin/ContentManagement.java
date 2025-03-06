package com.euroalu.controllers.admin;

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

import com.euroalu.models.Content;
import com.euroalu.models.Message;
import com.euroalu.services.CategoryService;
import com.euroalu.services.ContentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = { "/admin/quan-ly-noi-dung" })
public class ContentManagement {
	@Autowired
	private ContentService contentService;
	@Autowired
	CategoryService categoryService;
	Message message;

	@GetMapping
	public String showContentList(ModelMap model) throws Exception {
		// List<Content> contents = contentService.getAllContents();
		// model.addAttribute("contents", contents);
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/contents/index";
	}

	@GetMapping("/{id}")
	public String getByIdContent(ModelMap model, @PathVariable int id) throws Exception {
		Content content = contentService.getContentById(id);
		model.addAttribute("content", content);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/contents/edit";
	}

	@GetMapping("/them")
	public String getByIdContent(ModelMap model) throws Exception {
		model.addAttribute("content", new Content());
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/contents/add";
	}

	@PostMapping
	public String postContent(@ModelAttribute("content") Content content,
//			@PathVariable("idStudent") String studentId,
			HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Content contentCreated = contentService.saveContent(content, file, request);

		if (contentCreated == null) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-noi-dung/them"; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-noi-dung";
	}

	// Cập nhập nôi dung
	@PostMapping("/{id}")
	public String updateContent(@ModelAttribute("content") Content content, @PathVariable("id") Long id,
			HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		content.setId(id);
		Content contentCreated = contentService.updateContent(content, file, request);

		if (contentCreated == null) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-noi-dung/" + id; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-noi-dung";
	}
}
