package com.euroalu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VkuManagementSystemApplication {

	public static void main(String[] args) {
//		 LocalDate today = LocalDate.now();
//
//	        // Lấy thứ trong tuần của ngày hiện tại
//	        DayOfWeek dayOfWeek = today.getDayOfWeek();
//
//	        // In ra thứ trong tuần
//	        System.out.println("Hôm nay là thứ " + dayOfWeek.name());
		SpringApplication.run(VkuManagementSystemApplication.class, args);
//		System.out.println("THứ: " + dayOfWeek.getValue());
	}

}
