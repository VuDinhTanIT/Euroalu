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
import com.vku.models.Event;

@Service
public class EventService {

	private String apiURL ;
//	private String apiURL = Utils.LOCAL_STRING+"/admin/api/Event";
	private RestTemplate restTemplate = new RestTemplate();
//	private AppConfig appConfig;
	private HttpHeaders  headers = new HttpHeaders();
	
//	@Autowired
//	public EventService(AppConfig appConfig) {
//		this.appConfig = appConfig;
//	}
	public EventService() {
//        this.apiURL = api + "/admin/events";
        this.apiURL = Utils.BASE_URL + "/admin/events";
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }
	private List<Event> listEvents;

	public List<Event> getAllEvents() throws Exception {
//		System.out.println("url : " + apiURL); 
		RequestEntity<?> requestEntity = new RequestEntity<>("", HttpMethod.GET,
				URI.create(apiURL));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		listEvents = objectMapper.readValue(json, new TypeReference<List<Event>>() {
		});

		return listEvents;
	}
	
	public Event post(Event Event) {
		String api = apiURL ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<?> requestEntity = new RequestEntity<>(Event,
				HttpMethod.POST, URI.create(api));
		ResponseEntity<Event> response = restTemplate.exchange(requestEntity, Event.class);
		return response.getBody();
	}
//	public Event postAndGenerateQR(Event Event) {
//		String api = apiURL ;
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		RequestEntity<?> requestEntity = new RequestEntity<>(Event,
//				HttpMethod.POST, URI.create(api));
//		ResponseEntity<Event> response = restTemplate.exchange(requestEntity, Event.class);
//		return response.getBody();
//	}

	public ResponseEntity<String> Update(Event event, int id) {
		String api = apiURL+ "/"  + id;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Event> requestEntity = new HttpEntity<>(event);
		ResponseEntity<String> responseEntity = restTemplate.exchange(api, HttpMethod.PUT, requestEntity, String.class);
		return responseEntity;
	}

	public Event getEventById(int id) {
	    String api = apiURL + "/" + id;

	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    ResponseEntity<Event> responseEntity = restTemplate.exchange(api, HttpMethod.GET, requestEntity, Event.class);
	    return responseEntity.getBody();
	}
}
