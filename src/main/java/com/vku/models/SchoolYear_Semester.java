package com.vku.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolYear_Semester {

	private int id;
	private String schoolYear;
	private String semester;
	private String startTime;
	private String endTime;
	private String schoolYearStartDate;
	private boolean current;
	private boolean status;
	private Timestamp createTime;
	
	private Timestamp updateTime;
	

}
