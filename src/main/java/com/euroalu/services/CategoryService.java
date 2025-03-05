package com.euroalu.services;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.euroalu.models.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryService {

	private final String apiURL;
	private final ApiService apiService;

	public CategoryService() {
		this.apiURL = Utils.BASE_URL + "/admin/categories";
		this.apiService = new ApiService();
	}

	public List<Category> getAllCategorys() throws Exception {
		ResponseEntity<String> response = apiService.get(apiURL, String.class);
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			String json = response.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, new TypeReference<List<Category>>() {
			});
		}
		return null;
	}

	public Category post(Category category) {
		ResponseEntity<Category> response = apiService.post(apiURL, category, Category.class);
		return response != null ? response.getBody() : null;
	}

	public ResponseEntity<String> update(Category category, int id) {
		String api = apiURL + "/" + id;
		ResponseEntity<String> response = apiService.post(api, category, String.class);
		return response;
	}

	public Category getCategoryById(int id) {
		String api = apiURL + "/" + id;
		ResponseEntity<Category> response = apiService.get(api, Category.class);
		return response != null ? response.getBody() : null;
	}
}
