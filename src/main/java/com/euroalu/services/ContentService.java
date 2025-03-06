package com.euroalu.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.euroalu.models.Content;
import com.euroalu.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vku.Utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ContentService {

	private final String apiURL;
	private final ApiService apiService;

	public ContentService() {
		this.apiURL = Utils.BASE_URL + "/admin/noi-dung";
		this.apiService = new ApiService();
	}

	public List<Content> getAllContents() throws Exception {
		ResponseEntity<String> response = apiService.get(apiURL, String.class);
		if (response != null && response.getStatusCode().is2xxSuccessful()) {
			String json = response.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, new TypeReference<List<Content>>() {
			});
		}
		return null;

	}

	public Content getContentById(int id) throws Exception {
		String api = apiURL + "/" + id;
		ResponseEntity<Content> response = apiService.get(api, Content.class);

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}

	public Content saveContent(Content content, MultipartFile file, HttpServletRequest request) {
		String newFileName = handleFileUpload(content, file, request);
		if (newFileName != null)
			content.setImage(newFileName);
		ResponseEntity<Content> response = apiService.post(apiURL , content, Content.class);

		if (response != null && response.getStatusCode() == HttpStatus.CREATED) {
			return response.getBody();
		}
//		System.out.println(response);
		return null;

	}

	public Content getByPath(String path) throws Exception {
		String api = apiURL + "/blog/" + path;
		ResponseEntity<Content> response = apiService.get(api, Content.class);

		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {

			return null;
		}
	}

	public Content updateContent(Content content, MultipartFile file, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String newFileName = handleFileUpload(content, file, request);
		if (newFileName != null)
			content.setImage(newFileName);
		ResponseEntity<Content> response = apiService.put(apiURL + "/"+ content.getId(), content, Content.class);

		if (response != null && response.getStatusCode() == HttpStatus.CREATED) {
			return response.getBody();
		}
//		System.out.println(response);
		return null;
	}

	private String handleFileUpload(Content content, MultipartFile file, HttpServletRequest request) {
		if (file != null && !file.isEmpty()) {
			try {
				String newFileName = content.getPath() + ".jpg";
				String applicationPath = request.getServletContext().getRealPath("");
				String uploadFilePath = applicationPath + File.separator + Utils.UPLOAD_DIR;
				File uploadFolder = new File(uploadFilePath);

				if (!uploadFolder.exists()) {
					uploadFolder.mkdir();
				}

				// Xóa tệp cũ nếu tên tệp mới khác tên tệp cũ
				if (content.getImage() != null && !content.getImage().equals(newFileName)) {
					File oldImageFile = new File(uploadFilePath + File.separator + content.getImage());
					if (oldImageFile.exists()) {
						oldImageFile.delete(); // Xóa tệp cũ
					}
				}
				Files.copy(file.getInputStream(), new File(uploadFilePath + File.separator + newFileName).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
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
