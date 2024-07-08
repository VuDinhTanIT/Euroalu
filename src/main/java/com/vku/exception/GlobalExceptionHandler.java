//package com.vku.exception;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex, HttpServletRequest request) {
//        // Ghi log lỗi vào cơ sở dữ liệu
////        logErrorToDatabase(ex);
//        // Lấy thông tin lỗi và chuyển hướng đến trang lỗi tùy chỉnh
//        String errorMessage = "An error occurred: " + ex.getMessage();
//        request.setAttribute("errorMessage", errorMessage);
//        return "error";
//    }
//
////    private void logErrorToDatabase(Exception ex) {
////        // Ghi log lỗi vào cơ sở dữ liệu (ví dụ: sử dụng JPA repository)
////        ErrorLogger errorLog = new ErrorLog(ex.getMessage());
////        errorLogRepository.save(errorLog);
////    }
//}