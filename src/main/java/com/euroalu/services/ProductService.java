package com.euroalu.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.euroalu.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductService {

	private final String apiURL;
	private final ApiService apiService;

	public ProductService() {
		this.apiURL = Utils.BASE_URL + "/admin/products";
		this.apiService = new ApiService();
	}

	public List<Product> getAllProducts() throws JsonMappingException, JsonProcessingException {
			ResponseEntity<String> response = apiService.get(apiURL, String.class);
			if (response != null && response.getStatusCode().is2xxSuccessful()) {
				String json = response.getBody();
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(json, new TypeReference<List<Product>>() {
				});
			}
			return null;

	}
	public Product getProductById(int id) {
		String api = apiURL + "/" + id;
		ResponseEntity<Product> response = apiService.get(api, Product.class);

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}

	public Product saveProduct(Product product, MultipartFile file, HttpServletRequest request) {
		String newFileName = null;
		if (!file.isEmpty()) {
			try {
//					newFileName = file.getOriginalFilename() + "_" + product.getCategory().getId();
				newFileName = product.getName() + "_" + product.getCategory().getId() + ".jpg";
				String applicationPath = request.getServletContext().getRealPath("");
				String uploadFilePath = applicationPath + File.separator + Utils.UPLOAD_DIR;
				File uploadFolder = new File(uploadFilePath);
				if (!uploadFolder.exists()) {
					uploadFolder.mkdir();
				}

				File imageFile = new File(uploadFilePath + File.separator + newFileName);
				Files.copy(file.getInputStream(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				// Cập nhật đường dẫn tệp tin trong đối tượng product
				product.setImage(newFileName); // Giả sử bạn có thuộc tính này trong Product

			} catch (Exception e) {
				System.err.println("Lỗi : " + e.getMessage());
				e.printStackTrace();
				// Xử lý ngoại lệ nếu có lỗi xảy ra
//	                return "fail"; // Hoặc bạn có thể ném một ngoại lệ tùy chỉnh
			}
		}
		// return newFileName; // Hoặc giá trị khác để biểu thị thành công
		ResponseEntity<Product> response = apiService.post(apiURL, product, Product.class);

		if (response != null && response.getStatusCode() == HttpStatus.CREATED) {
			return response.getBody();
		}
		return null;

	}

}
