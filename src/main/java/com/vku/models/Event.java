package com.vku.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Event {


	private int id;
	private String name;
	private String location;
	private String period;
	private String urlEvent;
	private String QRCodeEvent;
	private boolean status;
	private Timestamp createTime;
	private Timestamp updateTime;

}
