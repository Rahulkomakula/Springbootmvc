package com.springbootmvc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductModel {
   
	private String name;
	private String brand;
	private String madeIn;
	private int quantity;
	private double price;
	private int discountrate;

}
