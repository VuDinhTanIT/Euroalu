package com.vku.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseInfoDTO {

	private Long id;
	private String studentCode;
	private String nameClass;
	private String nameStudent;
	private String courseName;
	private String courseCode;
	private Long courseId;
	private boolean present;
	
	


}