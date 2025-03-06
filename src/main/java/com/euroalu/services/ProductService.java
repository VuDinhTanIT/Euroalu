package com.euroalu.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.euroalu.Utils.Utils;
import com.euroalu.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductService {

	private final String apiURL;
	private final ApiService apiService;

	public ProductService() {
		this.apiURL = Utils.BASE_URL + "/admin/products";
		this.apiService = new ApiService();
	}

	public List<Product> getAllProducts() throws Exception {
			ResponseEntity<String> response = apiService.get(apiURL, String.class);
			if (response != null && response.getStatusCode().is2xxSuccessful()) {
				String json = response.getBody();
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(json, new TypeReference<List<Product>>() {
				});
			}
			return null;

	}
	public Product getProductById(int id) throws Exception {
		String api = apiURL + "/" + id;
		ResponseEntity<Product> response = apiService.get(api, Product.class);

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}

	public Product saveProduct(Product product, MultipartFile file, HttpServletRequest request) {
	    String newFileName = handleFileUpload(product, file, request);
	    
	    if (newFileName != null) {
	        product.setImage(newFileName); // Cập nhật tên tệp tin trong đối tượng product
	    }

	    ResponseEntity<Product> response = apiService.post(apiURL, product, Product.class);
	    return (response != null && response.getStatusCode() == HttpStatus.CREATED) ? response.getBody() : null;
	}

	public boolean updateProduct(Product product, MultipartFile file, HttpServletRequest request) {
	    String newFileName = handleFileUpload(product, file, request);

	    if (newFileName != null) {
	        product.setImage(newFileName); // Cập nhật tên tệp tin trong đối tượng product
	    }
	    System.out.println("product ser " + product);
	    ResponseEntity<?> response = apiService.put(apiURL + "/" + product.getId(), product, Object.class);
	    return (response != null && response.getStatusCode() == HttpStatus.OK) ? true : false;
//	    return null;
	}

	private String handleFileUpload(Product product, MultipartFile file, HttpServletRequest request) {
	    if (file != null && !file.isEmpty()) {
	        try {
	            String newFileName = product.getName() + "_" + product.getCategory().getId() + ".jpg";
	            String applicationPath = request.getServletContext().getRealPath("");
	            String uploadFilePath = applicationPath + File.separator + Utils.UPLOAD_DIR;
	            File uploadFolder = new File(uploadFilePath);

	            if (!uploadFolder.exists()) {
	                uploadFolder.mkdir();
	            }

	            // Xóa tệp cũ nếu tên tệp mới khác tên tệp cũ
	            if (product.getImage() != null && !product.getImage().equals(newFileName)) {
	                File oldImageFile = new File(uploadFilePath + File.separator + product.getImage());
	                if (oldImageFile.exists()) {
	                    oldImageFile.delete(); // Xóa tệp cũ
	                }
	            }

	            // Lưu tệp tin mới
	            Files.copy(file.getInputStream(), new File(uploadFilePath + File.separator + newFileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
	            return newFileName; // Trả về tên tệp mới
	        } catch (Exception e) {
	            System.err.println("Lỗi : " + e.getMessage());
	            e.printStackTrace();
	            // Xử lý ngoại lệ nếu có lỗi xảy ra
	        }
	    }
	    return null; // Nếu không có tệp tin mới, trả về null
	}

}
