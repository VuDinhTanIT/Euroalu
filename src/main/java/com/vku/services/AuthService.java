package com.vku.services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean authenticate(String username, String password) {
        // Thực hiện logic xác thực đăng nhập ở đây
        // Kiểm tra username và password, và trả về true nếu hợp lệ, ngược lại trả về false
        // Có thể kết hợp với cơ sở dữ liệu hoặc bất kỳ cơ chế xác thực nào khác
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }
// gọi tạm bên gusestService
//	public Guest loginGuest(String username, String password) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
