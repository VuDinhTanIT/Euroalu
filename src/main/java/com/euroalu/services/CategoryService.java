package com.euroalu.services;


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

import com.euroalu.models.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;

@Service
public class CategoryService {

	private String apiURL ;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Category";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public CategoryService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public CategoryService() {
//        this.apiURL = api + "/admin/categorys";
        this.apiURL = Utils.BASE_URL + "/admin/categories";
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
	private List<Category> listCategorys;

	public List<Category> getAllCategorys() throws Exception {
//		System.out.println("url : " + apiURL); 
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET,
				URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listCategorys = objectMapper.readValue(json, new TypeReference<List<Category>>() {
		});

		return listCategorys;
	}
	
	public Category post(Category Category) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(Category,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<Category> response = restTemplate.exchange(requestEntity, Category.class);
		return response.getBody();
	}
//	public Category postAndGenerateQR(Category Category) {
//		String api = apiURL ;
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		RequestEntity<?> requestEntity = new RequestEntity<>(Category,
//				HttpMethod.POST, URI.create(api));
//		ResponseEntity<Category> response = restTemplate.exchange(requestEntity, Category.class);
//		return response.getBody();
//	}

	public ResponseEntity<String> Update(Category category, int id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Category> requestEntity = new HttpEntity<>(category);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Category getCategoryById(int id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<Category> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Category.class);
	    return responseEntity.getBody();
	}
}
