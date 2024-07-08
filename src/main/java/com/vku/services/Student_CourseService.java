package com.vku.services;


import java.net.URI;
import java.util.List;

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
import com.vku.models.StudentCourseInfoDTO;
import com.vku.models.Student_Course;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class Student_CourseService {

	private String apiURL ;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Student_Course";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public Student_CourseService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public Student_CourseService() {
        this.apiURL = Utils.BASE_URL + "/admin/student_courses";
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

	public List<StudentCourseInfoDTO> getAllStudent_Courses() throws Exception {
		List<StudentCourseInfoDTO> listStudent_Courses ;
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET,
				URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listStudent_Courses = objectMapper.readValue(json, new TypeReference<List<StudentCourseInfoDTO>>() {
		});
		return listStudent_Courses;
	}

	
	public ResponseEntity<Student_Course> post(Student_Course student_Course) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(student_Course,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<Student_Course> response = restTemplate.exchange(requestEntity, Student_Course.class);
//		System.out.println("repose student: "+ response);
		return response;
	}

	public ResponseEntity<String> Update(Student_Course student_Course, int id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Student_Course> requestEntity = new HttpEntity<>(student_Course);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Student_Course getStudent_CourseById(int id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<Student_Course> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Student_Course.class);
	    return responseEntity.getBody();
	}
	
	public String importListStudent_CoursesFromExcel(List<Student_Course> list) {
		String api = apiURL + "/addList";
//		HttpHeaders headers = appConfig.cookieStore().getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(list,headers, HttpMethod.POST, URI.create(api));
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		return responseEntity.getBody();
	}

}
