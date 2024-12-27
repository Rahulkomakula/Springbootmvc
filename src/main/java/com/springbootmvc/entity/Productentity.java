package com.springbootmvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Productdata")
public class Productentity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
	    private String Name;
		private String Brand;
		private String MadeIn;
		private int quantity;
		private double price;
        private int discountrate;
        private double taxRate;
      //  @Column(name="Discountprice")
        private double discount_price;
        private double offerPrice;
        private double finalPrice;
        private double stockValue;
}
