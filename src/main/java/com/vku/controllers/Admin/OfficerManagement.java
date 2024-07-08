package com.vku.controllers.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vku.services.OfficerService;
import com.vku.services.PositionService;

import jakarta.servlet.http.HttpServletRequest;

import com.vku.models.Message;
import com.vku.models.Officer;
import com.vku.models.Position;

@Controller
@RequestMapping("/admin/officers")
public class OfficerManagement {

	@Autowired
	private OfficerService officerService;
	@Autowired
	private PositionService positionService;
	Message message ;
	@GetMapping
	public String showOfficerList(ModelMap model) throws Exception {
		List<Officer> officers = officerService.getAllOfficers();
//		System.out.println(" get name position: "+ officers.get(0));
		model.addAttribute("officers", officers);
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/officers/index"; // Returns the HTML template name
	}
	
	@GetMapping("/add")
	public String showOfficerAddUI(ModelMap model) throws Exception {
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		Officer officer = new Officer();
		model.addAttribute("officer", officer);
		model.addAttribute("positions", positionService.getAllPositions());
		return "admin/officers/add"; // Returns the HTML template name
	}
	@PostMapping("/add")
	public String PositonAdd(
			@ModelAttribute("officer")  Officer officer
//			HttpServletRequest request
			) {
		System.out.println(" ???"+officer);
		try {
//			Position position = new Position();
//			position.setId(Integer.parseInt(request.getParameter("positionCode")));
//			System.out.println("mẹ nó: " + officer + "\n PositionCode: " + Integer.parseInt(request.getParameter("positionCode")));
//			Position position = positionService.getPositionById(1) ;
//			officer.setPositionCode(position);
//			System.out.println("po : " + position);
			officerService.post(officer);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm chức vụ thành công!");
			return "redirect:/admin/officers";
			
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm chức vụ không thành công!");
			return "redirect:/admin/officers/add";
		}
	}
	
	@GetMapping("/{id}")
	public String showOfficerEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
		model.addAttribute("officer", officerService.getOfficerById(id));
		model.addAttribute("positions", positionService.getAllPositions());
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/officers/edit"; // Returns the HTML template name
	}
	@PostMapping("/{id}")
	public String OfficerUpdatate(ModelMap model, @PathVariable("id") int id, @ModelAttribute Officer Officer) throws Exception {
		message = new Message();
		try {
			officerService.Update(Officer, id);
			message.setStatus("success");
			message.setMessage("Cập nhập chức vụ thành công!");
			return "redirect:/admin/officers/" +id; 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setStatus("fail");
			message.setMessage("Cập nhập chức vụ thất bại!");
			return "redirect:/admin/officers/" +id; 
		}
	}
	
}
