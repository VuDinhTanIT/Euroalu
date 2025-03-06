package com.euroalu.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.euroalu.models.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;



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

	public Category getCategoryById(int id) throws Exception {
		String api = apiURL + "/" + id;
		ResponseEntity<Category> response = apiService.get(api, Category.class);
		return response != null ? response.getBody() : null;
	}
}
