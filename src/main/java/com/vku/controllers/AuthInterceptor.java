//package com.vku.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.vku.config.AppConfig;
//
//import jakarta.servlet.http.HttpServletResponseWrapper;
//
//
//public class AuthInterceptor implements HandlerInterceptor {
//    private static final String LOGIN_COOKIE_NAME = "loginCookie";
//    private static final String LOGGED_IN_ATTRIBUTE = "isLoggedIn";
//
//    @Autowired
//    private AppConfig appConfig;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponseWrapper response, Object handler) throws Exception {
//        // Kiểm tra token từ cookie
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (LOGIN_COOKIE_NAME.equals(cookie.getName()) && isValidToken(cookie.getValue())) {
//                    // Token hợp lệ, đánh dấu người dùng đã đăng nhập
//                    request.getSession().setAttribute(LOGGED_IN_ATTRIBUTE, true);
//                    break;
//                }
//            }
//        }
//
//        // Kiểm tra trạng thái đăng nhập
//        Boolean isLoggedIn = (Boolean) request.getSession().getAttribute(LOGGED_IN_ATTRIBUTE);
//        if (isLoggedIn == null || !isLoggedIn) {
//            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
//            response.sendRedirect("/admin/login");
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // Thực hiện các xử lý sau khi xử lý request
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // Thực hiện các xử lý sau khi hoàn thành request
//    }
//
//    private boolean isValidToken(String token) {
//        // Kiểm tra tính hợp lệ của token
//        // Đây là nơi bạn thực hiện xác thực token, ví dụ: kiểm tra trong cơ sở dữ liệu hoặc qua logic xác thực khác
//        // Trả về true nếu token hợp lệ, ngược lại trả về false
//        // Code ví dụ:
//        return "valid_token".equals(token);
//    }
//}