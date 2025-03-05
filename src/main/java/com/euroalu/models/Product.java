package com.euroalu.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Long id;
	private String name;
	private String description;

	private Category category;	
	
	private int productType;

	private double price;
	private String image;
	private boolean status = true;

}