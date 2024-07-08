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
import com.vku.models.Course;

@Service
public class CourseService {

	private String apiURL; 
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders headers = new HttpHeaders();

//	@Autowired
//	public courseService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public CourseService() {
		this.apiURL = Utils.BASE_URL + "/admin/courses";
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON);
	}

	private List<Course> listcourses;

	public List<Course> getAllCourses() throws Exception {
		System.out.println("url : " + apiURL);
		long startTime = System.currentTimeMillis();
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET, URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listcourses = objectMapper.readValue(json, new TypeReference<List<Course>>() {
		});

		// Kết thúc đếm thời gian
		long endTime = System.currentTimeMillis();

		// Tính toán thời gian đã trôi qua
		long elapsedTime = endTime - startTime;

		// Hiển thị thời gian đã trôi qua ở đơn vị mili giây
		System.out.println("Thời gian thực thi api: " + elapsedTime + " ms");
		return listcourses;
	}

	public Course getCourseById(int id) {
		String api = apiURL + "/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Course> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Course.class);
		return responseEntity.getBody();
	}

	public ResponseEntity<Course> post(Course course) {
		String api = apiURL;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(course, HttpMethod.POST, URI.create(api));
		ResponseEntity<Course> response = restTemplate.exchange(requestEntity, Course.class);
		System.out.println("repose course: " + response);
		return response;
	}

	public ResponseEntity<String> Update(Course course, int id) {
		String api = apiURL + "/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Course> requestEntity = new HttpEntity<>(course);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

}
