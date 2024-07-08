package com.vku.models;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vku.Utils.Utils;
import com.vku.services.StudentService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student_Course {
	private StudentService studentService;
	private Long id;
	private String studentCode;
	
	private int courseId;
	private boolean extra_sheet;

	private boolean status;
	private Timestamp createTime;
	private Timestamp updateTime;
	
//	public Student getInfoStudent() {
////		System.out.println(Utils.BASE_URL);
//		StudentService studentService = new StudentService();
//		return studentService.getStudentCode(studentCode) ;
//	}
}
