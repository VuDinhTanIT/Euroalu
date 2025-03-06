package com.euroalu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientError(HttpClientErrorException e, Model model) {
        String errorMessage;
        e.printStackTrace();
        if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
        	
            errorMessage = "Bad Request: " + e.getResponseBodyAsString();
        } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            errorMessage = "Not Found: " + e.getResponseBodyAsString();
        } else {
            errorMessage = "Unexpected error: " + e.getLocalizedMessage();
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error"; // Tên view (HTML) bạn muốn trả về
    }
    @ExceptionHandler(Exception.class)
    public String handleHttpClientError(Exception e, Model model) {
    	e.getCause();
    	System.err.println("lỗi " );
    	e.printStackTrace();
        model.addAttribute("errorMessage", e.getLocalizedMessage());
        return "error"; // Tên view (HTML) bạn muốn trả về
    }
}