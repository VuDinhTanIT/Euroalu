package com.vku.controllers.Admin;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.vku.services.Student_CourseService;

import com.vku.models.Message;
import com.vku.models.StudentCourseInfoDTO;
import com.vku.models.Student_Course;

@Controller
@RequestMapping("/admin/student-course-management")
public class Student_Course_Management {

	@Autowired
	private Student_CourseService student_courseService;

	Message message;

	@GetMapping
	public String showStudent_CourseList(ModelMap model) throws Exception {
		List<StudentCourseInfoDTO> student_courses = student_courseService.getAllStudent_Courses();
		model.addAttribute("student_courses", student_courses);

		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/student_course/index"; // Returns the HTML template name
	}

	@GetMapping("/add")
	public String showStudent_CourseAddUI(ModelMap model) throws Exception {
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		Student_Course student_course = new Student_Course();
		model.addAttribute("student_course", student_course);
		return "admin/student_course/add"; // Returns the HTML template name
	}

	@PostMapping("/add")
	public String Student_CourseAdd(@ModelAttribute("student_course") Student_Course student_course) {
		try {
			student_courseService.post(student_course);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm thành công!");
			return "redirect:/admin/student-course-management";

		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm không thành công!");
			return "redirect:/admin/student-course-management/add";
		}
	}

	@GetMapping("/{id}")
	public String showStudent_CourseEditUI(ModelMap model, @PathVariable("id") int id) throws Exception {
		System.out.println("ma StudentCourse: " + id);
		model.addAttribute("student_courses", student_courseService.getStudent_CourseById(id));
		System.out.println("passsssss: " + id);
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/student_course/edit"; // Returns the HTML template name
	}

	@PostMapping("/{id}")
	public String Student_CourseUpdatate(ModelMap model, @PathVariable("id") int id,
			@ModelAttribute Student_Course Student_Course) throws Exception {
		message = new Message();
		try {
			student_courseService.Update(Student_Course, id);
			message.setStatus("success");
			message.setMessage("Cập nhập thành công!");
			return "redirect:/admin/student-course-management/" + id;

		} catch (Exception e) {
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập thất bại!");
			return "redirect:/admin/student-course-management/" + id;
		}
	}

//	@RequestMapping(method = RequestMethod.POST, path = "/importExcel")
	@PostMapping("importExcel")
	public ResponseEntity<Boolean> importExcel(@RequestBody MultipartFile file) throws Exception {
		// tạo thư mục tạm để lưu file
		String tempDir = System.getProperty("java.io.tmpdir");
		Path filePath = Paths.get(tempDir, file.getOriginalFilename());
		Files.write(filePath, file.getBytes());
		message = new Message();
		try {
			FileInputStream fis = new FileInputStream(filePath.toFile());
			Workbook workbook = WorkbookFactory.create(fis);

			// Lấy sheet đầu tiên
			Sheet sheet = workbook.getSheetAt(0);

			// Tạo danh sách sv
			List<Student_Course> student_courses = new ArrayList<>();

			for (Row row : sheet) {
				if (row.getRowNum() != 0) {
					Student_Course student_course = new Student_Course();
					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == CellType.STRING) {
								student_course.setStudentCode(cell.getStringCellValue());
							} else {
								throw new Exception("Invalid data type for student code. Expected string.");
							}
							break;

						case 1:
							if (cell.getCellType() == CellType.NUMERIC) {
								student_course.setCourseId((int) cell.getNumericCellValue());
							} else {
								throw new Exception("Invalid data type for course ID. Expected numeric.");
							}
							break;
						default:
							// Ignore other columns
							break;
						}
					}
					student_courses.add(student_course);
				}
			}
			fis.close();
			Files.delete(filePath);

//			String rs = "OK";
			System.out.println("số row: "+ sheet.getLastRowNum()+"<=" + student_courses.size() +" - "+student_courses.get(0));
			if (student_courses.size() >= sheet.getLastRowNum()) {
				String rs = student_courseService.importListStudent_CoursesFromExcel(student_courses);
				System.out.println("rs: " + rs);
				message.setStatus("success");
				message.setMessage("Thực hiện thành công!");
				return ResponseEntity.ok(true);
			} else {
				message.setStatus("fail");
				message.setMessage("Import thất bại. Kiểm tra dữ liệu import!");
//			return "redirect:/admin/student-course-management";
				return ResponseEntity.ok(false);
			}
		} catch (Exception e) {
			// Handle other exceptions
			e.printStackTrace();
			message.setStatus("fail");
			message.setMessage("Error processing Excel file." + e.getMessage());
			return ResponseEntity.ok(false);
		}

	}

}
