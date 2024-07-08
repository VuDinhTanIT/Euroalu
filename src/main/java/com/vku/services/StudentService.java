package com.vku.services;


import java.net.URI;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;
import com.vku.models.Student;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class StudentService {

	private String apiURL ;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Student";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public StudentService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public StudentService() {
        this.apiURL = Utils.BASE_URL + "/admin/students";
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
	private List<Student> listStudents;

	public List<Student> getAllStudents() throws Exception {
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET,
				URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listStudents = objectMapper.readValue(json, new TypeReference<List<Student>>() {
		});
		return listStudents;
	}

	
	public ResponseEntity<Student> post(Student Student) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(Student,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<Student> response = restTemplate.exchange(requestEntity, Student.class);
//		System.out.println("repose student: "+ response);
		return response;
	}

	public ResponseEntity<String> Update(Student student, String id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Student> requestEntity = new HttpEntity<>(student);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Student getStudentCode(String id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<Student> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Student.class);
	    return responseEntity.getBody();
	}
	
	public String importListStudentsFromExcel(List<Student> list) {
		String api = apiURL + "/addList";
//		HttpHeaders headers = appConfig.cookieStore().getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(list,headers, HttpMethod.POST, URI.create(api));
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		return responseEntity.getBody();
	}
//	public void generateExcelctsv(HttpServletResponse response) throws Exception {
//
//		List<Student> students = getAllStudents();
//
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet("Index Info");
//		HSSFRow row = sheet.createRow(0);
//
//		row.createCell(0).setCellValue("STT");
//		row.createCell(1).setCellValue("Fullname");
//		row.createCell(2).setCellValue("Email cá nhân - Email VKU");
//		row.createCell(3).setCellValue("BHYT");
//		row.createCell(4).setCellValue("CMND/CCCD");
//		row.createCell(5).setCellValue("Ngày Sinh");
//		row.createCell(6).setCellValue("Lớp");
//		row.createCell(7).setCellValue("Mã sinh viên");
//		row.createCell(8).setCellValue("Loại hồ sơ đã nộp");
//		row.createCell(9).setCellValue("Đã nhập học & chưa nhập học bộ");
//		row.createCell(10).setCellValue("Đã nhập học & chưa nhập học Trường");
//		row.createCell(11).setCellValue("Trạng thái nộp hồ sơ");
//
//		int dataRowIndex = 1;
//		int STT = 1; // Initialize the ID
//
//		for (Student student : students) {
//			HSSFRow dataRow = sheet.createRow(dataRowIndex);
//
//			dataRow.createCell(0).setCellValue(STT); // Set the ID
////			dataRow.createCell(1).setCellValue(student.getFullName());
////			dataRow.createCell(2).setCellValue(student.getEmail() );
////			dataRow.createCell(3).setCellValue(student.getCodeBHYT());
////			dataRow.createCell(4).setCellValue(student.getNumberCIC());
////			dataRow.createCell(5).setCellValue(student.getBirthday());
////			dataRow.createCell(6).setCellValue(student.getClassName());
////			dataRow.createCell(7).setCellValue(student.getIdStudent());
//
//			dataRowIndex++;
//			STT++; // Increment the ID
//		}
//		for (int i = 0; i < 9; i++) {
//			sheet.autoSizeColumn(i);
//		}
//		ServletOutputStream ops = response.getOutputStream();
//		workbook.write(ops);
//		workbook.close();
//		ops.close();
//	}
}
