	package com.vku.models;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

	private String studentCode;
	private String password = " ";
	private String cccd;
	private String name;
	private String birthDay;
	private String email;
	private String phoneNumber;
	private String familyPoneNumber;
	private String gender;
	private String birthPlace;
	private String address;
	private String className;
	private String groupCodes;
	private String QRCodeImage;
	private boolean status;

	private Timestamp createTime;
	private Timestamp updateTime;

}
