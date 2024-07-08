package com.vku.controllers.Admin;

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
import com.vku.models.Message;
import com.vku.Utils.Utils;
import com.vku.models.Event;

@Controller
@RequestMapping("/admin/events")
public class EventManagement {

	@Autowired
	private EventService eventService;
	Message message ;
	@GetMapping
	public String showEventList(ModelMap model) throws Exception {
		List<Event> events = eventService.getAllEvents();
		String pathImage = Utils.URL_IMAGE;
//		System.out.println("EventManager "+ events);
		model.addAttribute("events", events);
		model.addAttribute("path_image", pathImage);

		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/events/index"; // Returns the HTML template name
	}
	
	@GetMapping("/add")
	public String showEventAddUI(ModelMap model) throws Exception {
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/events/add"; // Returns the HTML template name
	}
	@PostMapping("/add")
	public String PositonAdd(@ModelAttribute Event event) {
		try {
			Event eventCreated = eventService.post(event);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm sự kiện thành công!");
			return "redirect:/admin/events";
			
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sự kiện không thành công!" + e.getMessage());
			return "redirect:/admin/events/add";
		}
	}
	
	@GetMapping("/{id}")
	public String showEventEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
		model.addAttribute("event", eventService.getEventById(id));
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/events/edit"; // Returns the HTML template name
	}
	@PostMapping("/{id}")
	public String EventUpdatate(ModelMap model, @PathVariable("id") int id, @ModelAttribute Event event) throws Exception {
		message = new Message();
		try {
			eventService.Update(event, id);
			message.setStatus("success");
			message.setMessage("Cập nhập sự kiện thành công!");
			return "redirect:/admin/events/" +id; 
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập sự kiện thất bại!");
			return "redirect:/admin/events/" +id; 
		}
	}
	
}
