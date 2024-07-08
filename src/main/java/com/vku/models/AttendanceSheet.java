package com.vku.models;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AttendanceSheet {

	private Long id;
	private String lessonContent;

	private Long courseId;
	private boolean status;
	private String teachDate ;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	

}
