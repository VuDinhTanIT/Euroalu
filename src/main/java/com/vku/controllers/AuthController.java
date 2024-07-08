package com.vku.controllers;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.vku.models.Guest;
import com.vku.models.LoginRequest;
import com.vku.services.AuthService;
import com.vku.services.GuestService;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;
	@Autowired
	private GuestService guestService;

	@GetMapping("admin/login")
	public String showLoginForm() {
		// Trả về trang đăng nhập của giao diện web
		return "admin/login";
	}

	@PostMapping("admin/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			ModelMap model) {
		boolean isAuthenticated = authService.authenticate(username, password);
		if (isAuthenticated) {
			// Xử lý khi đăng nhập thành công
			model.addAttribute("message", "Đăng nhập thành công!");
			return "redirect:/admin/positions";
		} else {
			// Xử lý khi đăng nhập thất bại
			model.addAttribute("message", "Đăng nhập thất bại!");
			return "redirect:/admin/login";
		}
	}

//    Guest - khách
	@GetMapping("/login")
	public String show(ModelMap model, @ModelAttribute("loginRequest") LoginRequest loginRequest) {
		model.addAttribute("loginRequest", loginRequest);

		// Trả về trang đăng nhập của giao diện web
		return "guests/login";
	}

	@PostMapping("/login")
	public String loginGuest(
//    		@RequestParam("cccd") String username, @RequestParam("phoneNumber") String password, 
			LoginRequest loginRequest, @RequestParam("captchaInput") String captchaInput,
			RedirectAttributesModelMap model, HttpSession session) {
		String sessionCaptcha = (String) session.getAttribute("captcha");

		// Validate the CAPTCHA input
		if (captchaInput == null || sessionCaptcha == null || !captchaInput.equals(sessionCaptcha)) {
			model.addFlashAttribute("loginRequest", loginRequest);
			model.addFlashAttribute("error", "Nhập mã CAPTCHA không đúng!");
			return "redirect:/login"; // Display login page with error message if login or CAPTCHA is incorrect
		}
		Guest guest = guestService.loginGuest(loginRequest);
		if (guest != null) {
			// Xử lý khi đăng nhập thành công
//			Message.("message", "Đăng nhập thành công!");
		} else {
			// Thêm khách khi chưa có dữ liệu trong db
			Guest newGuest = new Guest();
			newGuest.setName(loginRequest.getUsername());
			newGuest.setPhoneNumber(loginRequest.getPassword());
			guest = guestService.post(newGuest);
//            return "redirect:/admin/login";
		}
		session.setAttribute("guest", guest);
//		model.addFlashAttribute("guest", guest);
		return "redirect:/guests";
	}

	// Mã captcha
	@GetMapping("/captcha")
	public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Captcha captcha = new Captcha.Builder(200, 50).addText().addNoise()
				.addBackground(new GradiatedBackgroundProducer()).build();

		// Lưu captcha vào session
		request.getSession().setAttribute("captcha", captcha.getAnswer());

		// Đưa captcha vào response
		response.setContentType("image/png");
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(captcha.getImage(), "png", outputStream);
		outputStream.flush();
		outputStream.close();
	}

}