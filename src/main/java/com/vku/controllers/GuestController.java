package com.vku.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vku.services.EventService;
import com.vku.services.GuestService;

import jakarta.servlet.http.HttpSession;

import com.vku.models.Message;
import com.vku.Utils.Utils;
import com.vku.models.Event;
import com.vku.models.Guest;

@Controller
@RequestMapping(value = { "/guests" ,"/"})
public class GuestController {

	@Autowired
	private GuestService guestService;
	@Autowired
	private EventService eventService;
	Message message;

	
	@GetMapping
	public String showGuestList(ModelMap model, HttpSession session) throws Exception {
		String pathImage = Utils.URL_IMAGE;
		List<Event> events = eventService.getAllEvents();
//		System.out.println(guests.get(0));
		Guest guest = (Guest) session.getAttribute("guest");
		if (guest == null) {
			return "redirect:login";
		}
		model.addAttribute("events", events);
		System.out.println("index_QR: " + guest);
		model.addAttribute("guest", guest);
		model.addAttribute("path_image", pathImage);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "guests/index_QR"; // Returns the HTML template name
	}
	@GetMapping("/logout")
	public String LogoutGuest(ModelMap model, HttpSession session) throws Exception {
//		System.out.println(guests.get(0));
		session.removeAttribute("guest");
		return "redirect:login";

	}

//	@GetMapping("/add")
//	public String showGuestAddUI(ModelMap model) throws Exception {
//		if (message != null) {
//			model.addAttribute("message", message);
//			message = null;
//		}
//		return "guests/add"; // Returns the HTML template name
//	}

	@PostMapping()
	public String CreateQRcode(@ModelAttribute Guest guest, HttpSession session) {
		try {
			session.setAttribute("guest", guestService.creatQRCode(guest));
			message = new Message();
			message.setStatus("success");
			message.setMessage("Tạo mã QR thành công");
			return "redirect:/guests";

		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thất bại!!!!!!" + e.getMessage());
			return "redirect:/guests";
		}
	}

//	@GetMapping("/{id}")
//	public String showGuestEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
//		model.addAttribute("path_image", pathImage);
//		model.addAttribute("guest", guestService.getGuestById(id));
//		if (message != null) {
//			model.addAttribute("message", message);
//			message = null;
//		}
//		return "guests/edit"; // Returns the HTML template name
//	}

	@PostMapping("/{id}")
	public String GuestUpdatate(ModelMap model, @PathVariable("id") int id, @ModelAttribute Guest guest)
			throws Exception {
		message = new Message();
		try {
			guestService.Update(guest, id);
			message.setStatus("success");
			message.setMessage("Cập nhập chức vụ thành công!");
			return "redirect:/guests/" + id;

		} catch (Exception e) {
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập chức vụ thất bại!");
			return "redirect:/guests/" + id;
		}
	}

}
