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
//@JsonIgnoreType
public class Officer {


	private int id;
	private String officerCode;
	private String password = " ";
	private String name;
	private String email;
	private String cccd;
	private Position position;
	private String degree;
	private String salary;
	private String allowance;
	private String QRCodeImage;
	private boolean status;
	private Timestamp createTime;
	private Timestamp updateTime;
}
