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

import com.vku.services.PositionService;
import com.vku.models.Message;
import com.vku.models.Position;

@Controller
@RequestMapping("/admin/positions")
public class PositionManagement {

	@Autowired
	private PositionService positionService;
	Message message ;
	@GetMapping
	public String showPositionList(ModelMap model) throws Exception {
		List<Position> positions = positionService.getAllPositions();
//		System.out.println(positions.get(0));
		model.addAttribute("positions", positions);
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/positions/index"; // Returns the HTML template name
	}
	
	@GetMapping("/add")
	public String showPositionAddUI(ModelMap model) throws Exception {
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/positions/add"; // Returns the HTML template name
	}
	@PostMapping("/add")
	public String PositonAdd(@ModelAttribute Position position) {
		try {
			positionService.post(position);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm chức vụ thành công!");
			return "redirect:/admin/positions";
			
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm chức vụ không thành công!" + e.getMessage());
			return "redirect:/admin/positions/add";
		}
	}
	
	@GetMapping("/{id}")
	public String showPositionEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
		model.addAttribute("position", positionService.getPositionById(id));
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/positions/edit"; // Returns the HTML template name
	}
	@PostMapping("/{id}")
	public String PositionUpdatate(ModelMap model, @PathVariable("id") int id, @ModelAttribute Position position) throws Exception {
		message = new Message();
		try {
			positionService.Update(position, id);
			message.setStatus("success");
			message.setMessage("Cập nhập chức vụ thành công!");
			return "redirect:/admin/positions/" +id; 
			
		} catch (Exception e) {
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập chức vụ thất bại!");
			return "redirect:/admin/positions/" +id; 
		}
	}
	
}
