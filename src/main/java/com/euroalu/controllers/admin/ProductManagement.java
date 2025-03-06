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

import com.euroalu.models.Product;
import com.euroalu.models.Category;
import com.euroalu.models.Message;
import com.euroalu.services.CategoryService;
import com.euroalu.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = { "/admin/quan-ly-san-pham" })
public class ProductManagement {
	@Autowired
	private ProductService productService;
	@Autowired
	CategoryService categoryService;
	Message message;

	@GetMapping
	public String showProductList(ModelMap model) throws Exception {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/products/index";
	}

	@GetMapping("/{id}")
	public String getByIdProduct(ModelMap model, @PathVariable int id) throws Exception {
		Product product = productService.getProductById(id);
		List<Category> categories = categoryService.getAllCategorys();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/products/edit";
	}

	@GetMapping("/them")
	public String getByIdProduct(ModelMap model) throws Exception {

		List<Category> categories = categoryService.getAllCategorys();
		model.addAttribute("categories", categories);
		model.addAttribute("product", new Product());
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/products/add";
	}

	@PostMapping
	public String postProduct(@ModelAttribute("product") Product product,
//			@PathVariable("idStudent") String studentId,
			HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Product productCreated = productService.saveProduct(product, file, request);

		if (productCreated == null) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-san-pham/them"; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-danh-muc";
	}

	@PostMapping("/{id}")
	public String updateProduct(@ModelAttribute("product") Product product, @PathVariable("id") Long id,
			HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		product.setId(id);
		boolean b = productService.updateProduct(product, file, request);

		if (!b) {
			// Xử lý lỗi
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sản phẩm không thành công! Vui lòng kiểm tra lại file");
			return "redirect:/admin/quan-ly-san-pham/" + id; // Chuyển hướng đến trang khác hoặc thông báo lỗi
		}

		return "redirect:/admin/quan-ly-danh-muc";
	}
}
