package com.euroalu.services;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

public class ApiService {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public ApiService() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
        } catch (HttpClientErrorException e) {
            handleHttpClientError(e);
        } catch (Exception e) {
            handleGenericError(e);
        }
        return null;
    }

    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
        try {
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        } catch (HttpClientErrorException e) {
            handleHttpClientError(e);
        } catch (Exception e) {
            handleGenericError(e);
        }
        return null;
    }

    // Các phương thức khác như PUT, DELETE có thể được thêm vào tương tự

    private void handleHttpClientError(HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
            System.err.println("Bad Request: " + e.getResponseBodyAsString());
        } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.err.println("Not Found: " + e.getResponseBodyAsString());
        } else {
            System.err.println("Unexpected error: " + e.getStatusCode());
            e.printStackTrace();
        }
    }

    private void handleGenericError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
}