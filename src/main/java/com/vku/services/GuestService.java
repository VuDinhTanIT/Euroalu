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
import com.vku.models.Guest;
import com.vku.models.LoginRequest;

@Service
public class GuestService {

	private String apiURL;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Guest";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders headers = new HttpHeaders();

//	@Autowired
//	public GuestService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public GuestService() {
		this.apiURL = Utils.BASE_URL + "/guests";
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON);
	}

	private List<Guest> listGuests;

	public List<Guest> getAllGuests() throws Exception {
//		System.out.println(apiURL);
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET, URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listGuests = objectMapper.readValue(json, new TypeReference<List<Guest>>() {
		});

		return listGuests;
	}

	public Guest post(Guest guest) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(guest, HttpMethod.POST, URI.create(api));
		ResponseEntity<Guest> response = restTemplate.exchange(requestEntity, Guest.class);
		return response.getBody();
	}

	public Guest creatQRCode(Guest guest) {
		String api = apiURL + "/createQR" ;
		System.out.println("index_QR_servie: " + guest);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Guest> requestEntity = new HttpEntity<>(guest);
		ResponseEntity<Guest> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, Guest.class);
		return responseEntity.getBody();
	}

	public ResponseEntity<String> Update(Guest guest, int id) {
		String api = apiURL + "/" + id;
//		System.out.println("index_QR_servie: " + guest);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Guest> requestEntity = new HttpEntity<>(guest);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Guest getGuestById(int id) {
		String api = apiURL + "/" + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Guest> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Guest.class);
		return responseEntity.getBody();
	}

	public Guest loginGuest(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		String api = Utils.BASE_URL + "/guests/login";
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(loginRequest, HttpMethod.POST, URI.create(api));
		ResponseEntity<Guest> response = restTemplate.exchange(requestEntity, Guest.class);
		return response.getBody();
	}
}
