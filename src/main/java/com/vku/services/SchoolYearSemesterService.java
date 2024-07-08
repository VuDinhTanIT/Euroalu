package com.vku.services;


import java.net.URI;
import java.util.List;

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
import com.vku.models.SchoolYear_Semester;

@Service
public class SchoolYearSemesterService {

	private String apiURL ;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/SchoolYear_Semester";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public SchoolYear_SemesterService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public SchoolYearSemesterService() {
//        this.apiURL = api + "/admin/schoolYearSemesters";
        this.apiURL = Utils.BASE_URL + "/admin/school_year_semesters";
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
	private List<SchoolYear_Semester> listSchoolYear_Semesters;

	public List<SchoolYear_Semester> getAllSchoolYearSemesters() throws Exception {
//		System.out.println("url : " + apiURL); 
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET,
				URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listSchoolYear_Semesters = objectMapper.readValue(json, new TypeReference<List<SchoolYear_Semester>>() {
		});

		return listSchoolYear_Semesters;
	}
	
	public SchoolYear_Semester post(SchoolYear_Semester SchoolYear_Semester) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(SchoolYear_Semester,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<SchoolYear_Semester> response = restTemplate.exchange(requestEntity, SchoolYear_Semester.class);
		return response.getBody();
	}
//	public SchoolYear_Semester postAndGenerateQR(SchoolYear_Semester SchoolYear_Semester) {
//		String api = apiURL ;
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		RequestEntity<?> requestEntity = new RequestEntity<>(SchoolYear_Semester,
//				HttpMethod.POST, URI.create(api));
//		ResponseEntity<SchoolYear_Semester> response = restTemplate.exchange(requestEntity, SchoolYear_Semester.class);
//		return response.getBody();
//	}

	public ResponseEntity<String> Update(SchoolYear_Semester schoolYearSemester, int id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SchoolYear_Semester> requestEntity = new HttpEntity<>(schoolYearSemester);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public SchoolYear_Semester getSchoolYear_SemesterById(int id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<SchoolYear_Semester> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, SchoolYear_Semester.class);
	    return responseEntity.getBody();
	}
}
