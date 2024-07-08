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

public class Position {


	private int id;
	private String positionCode;
	private String name;
	private boolean status;
	private Timestamp createTime;
	private Timestamp updateTime;

		
}
