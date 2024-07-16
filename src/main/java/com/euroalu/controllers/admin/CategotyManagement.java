package com.euroalu.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/admin/quan-ly-danh-muc"})
public class CategotyManagement {
	@GetMapping
	public String in() {
		return "admin/categories/index";
	}

}
