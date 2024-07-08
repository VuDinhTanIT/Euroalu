package com.vku.models;




import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreType
public class Course {

	private Long id;
	private String courseCode;
	private String name;
	private String week;
	private String room;
//	Thứ trong tuần
	private String dayOfWeek;
//	@Nullable
	private SchoolYear_Semester schoolYearSemester;
	private int semester;
//	Tiết 1-10
	private String period;
	private String qrCodeImageBase64;
	private boolean status;
	private Officer officer;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	

}
