package com.vku.services;


import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vku.Utils.Utils;
import com.vku.models.Position;

@Service
public class LoginService {

	private String apiURL = Utils.BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public PositionService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public LoginService() {
        
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

	
	public ResponseEntity<Position> post(Position Position) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(Position,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<Position> response = restTemplate.exchange(requestEntity, Position.class);
		return response;
	}

	public ResponseEntity<String> Update(Position position, int id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Position> requestEntity = new HttpEntity<>(position);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Position getPositionById(int id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<Position> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Position.class);
	    return responseEntity.getBody();
	}
}
