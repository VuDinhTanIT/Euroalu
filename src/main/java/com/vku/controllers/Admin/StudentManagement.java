package com.vku.controllers.Admin;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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

import com.vku.services.StudentService;

import com.vku.models.Message;
import com.vku.models.Student;

@Controller
@RequestMapping("/admin/students-management")
public class StudentManagement {

	@Autowired
	private StudentService studentService;

	Message message;

	@GetMapping
	public String showStudentList(ModelMap model) throws Exception {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
//		System.out.println(students.get(0));
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/students/index"; // Returns the HTML template name
	}

	@GetMapping("/add")
	public String showStudentAddUI(ModelMap model) throws Exception {
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		Student student = new Student();
		model.addAttribute("student", student);
		return "admin/students/add"; // Returns the HTML template name
	}

	@PostMapping("/add")
	public String StudentAdd(@ModelAttribute("student") Student student) {
		try {
			studentService.post(student);
			message = new Message();
			message.setStatus("success");
			message.setMessage("Thêm sinh viên thành công!");
			return "redirect:/admin/students";

		} catch (Exception e) {
			e.printStackTrace();
			message = new Message();
			message.setStatus("fail");
			message.setMessage("Thêm sinh viên không thành công!");
			return "redirect:/admin/students/add";
		}
	}

	@GetMapping("/{id}")
	public String showStudentEditUI(ModelMap model, @PathVariable("id") String id) throws Exception {
		System.out.println("ma SV: " + id);
		model.addAttribute("student", studentService.getStudentCode(id));
		System.out.println(studentService.getStudentCode(id));
		if (message != null) {
			model.addAttribute("message", message);
			message = null;
		}
		return "admin/students/edit"; // Returns the HTML template name
	}

	@PostMapping("/{id}")
	public String StudentUpdatate(ModelMap model, @PathVariable("id") String id, @ModelAttribute Student Student)
			throws Exception {
		message = new Message();
		try {
			studentService.Update(Student, id);
			message.setStatus("success");
			message.setMessage("Cập nhập sinh viên thành công!");
			return "redirect:/admin/students-management/" + id;

		} catch (Exception e) {
			// TODO: handle exception
			message.setStatus("fail");
			message.setMessage("Cập nhập sinh viên thất bại!");
			return "redirect:/admin/students-management/" + id;
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

			// lấy sheet đầu tiền
			Sheet sheet = workbook.getSheetAt(0);
			// lấy khóa mới nhất

			// tạo danh sách sv
			List<Student> students = new ArrayList<>();
			for (Row row : sheet) {
				if (row.getRowNum() != 0) {
					Student student = new Student();
					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 0: {
							student.setName(cell.getStringCellValue());
							break;
						}
						case 1: {
							student.setCccd(cell.getStringCellValue());
							break;
						}

						case 2: {
							student.setBirthDay(cell.getStringCellValue());

							break;
						}
						case 3: {
							student.setClassName(cell.getStringCellValue());
							break;
						}
						case 4: {
							student.setStudentCode(cell.getStringCellValue());
						}
						case 6: {
							student.setEmail(cell.getStringCellValue());
							break;
						}
//					
//					case 8: {
//						student.setBirthPlace(cell.getStringCellValue());
//						break;
//					}

						default:

						}

					}
					student.setStatus(true);
					student.setPassword("defaut");
//				System.out.println(student);
					students.add(student);
				}
			}
			fis.close();
			Files.delete(filePath);
			System.out.println(students!=null ?  students.get(0):"Lỗi list");	
			if (students.size() >= sheet.getLastRowNum()) {
				String rs = studentService.importListStudentsFromExcel(students);
				message.setStatus("success");
				message.setMessage("Thực hiện thành công!");
				return ResponseEntity.ok(true);
			} else {
				message.setStatus("fail");
				message.setMessage("Import thất bại. Kiểm tra dữ liệu đã nhâp!");
//			return "redirect:/admin/students";
				return ResponseEntity.ok(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setStatus("fail");
			message.setMessage("Error processing Excel file." + e.getMessage());
			return ResponseEntity.ok(false);
		}

	}

}
