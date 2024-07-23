package com.euroalu.models;

import java.sql.Timestamp;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	private Long id;

	private String name;

	private Long parentId;
	private Timestamp createTime;

	private Timestamp updateTime;

	private boolean status;

}