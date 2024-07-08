package com.vku.controllers.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vku.services.CourseService;
import com.vku.services.OfficerService;
import com.vku.services.SchoolYearSemesterService;
import com.vku.models.Message;
import com.vku.models.Course;

@Controller
@RequestMapping("/admin/courses")
public class CourseManagement {

	@Autowired
	private CourseService courseService;
	@Autowired
	private OfficerService officerService;
	@Autowired
	private SchoolYearSemesterService schoolYearSemesterService;
	Message message ;
	@GetMapping
	public String showCourseList(ModelMap model) throws Exception {
		List<Course> Courses = courseService.getAllCourses();
		model.addAttribute("courses", Courses);
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/courses/index"; // Returns the HTML template name
	}
	
	@GetMapping("/add")
	public String showCourseAddUI(ModelMap model) throws Exception {
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		Course Course = new Course();
		model.addAttribute("course", Course);
		model.addAttribute("officers", officerService.getAllOfficers());
		return "admin/courses/add"; // Returns the HTML template name
	}
	@PostMapping("/add")
	public String PositonAdd(
			@ModelAttribute("course")  Course Course
//			HttpServletRequest request
			) {
//		System.out.println(" ???"+Course);
		try {
			System.out.println("Course Manager: courseAdd: " + Course);

			courseService.post(Course);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm thành công!");
			return "redirect:/admin/courses";
			
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm không thành công!");
			return "redirect:/admin/courses/add";
		}
	}
	
	@GetMapping("/{id}")
	public String showCourseEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
		model.addAttribute("course", courseService.getCourseById(id));
		model.addAttribute("officers", officerService.getAllOfficers());
		model.addAttribute("schoolYearSemesters", schoolYearSemesterService.getAllSchoolYearSemesters());
		if (message != null)
		{
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/courses/edit"; // Returns the HTML template name
	}
	@PostMapping("/{id}")
	public String CourseUpdatate(ModelMap model, @PathVariable("id") int id, @ModelAttribute Course Course) throws Exception {
		message = new Message();
		try {
			courseService.Update(Course, id);
			System.out.println("CourseManager: Upadate - CourseUpdate: " + Course);
			message.setStatus("success");
			message.setMessage("Cập nhập thành công!");
			return "redirect:/admin/courses/" +id; 
			
		} catch (Exception e) {
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập thất bại!");
			return "redirect:/admin/courses/" +id; 
		}
	}
	
}
