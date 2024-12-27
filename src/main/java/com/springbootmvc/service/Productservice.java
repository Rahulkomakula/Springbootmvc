package com.springbootmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springbootmvc.entity.Productentity;
import com.springbootmvc.model.ProductModel;
import com.springbootmvc.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
@Service
@Data
@AllArgsConstructor
public class Productservice {
@Autowired

 ProductRepository productRepository;

 public void saveproductsdetails(ProductModel productmodel) {

	    double taxRate = productmodel.getPrice() * 18 / 100;
	    double discountprice = productmodel.getPrice() * productmodel.getDiscountrate() / 100;
	    double offerprice = productmodel.getPrice() - discountprice;
	    double finalprice = offerprice + taxRate;
	    double stockvalue = finalprice * productmodel.getDiscountrate();



	    Productentity productentity = new Productentity();
	    productentity.setName(productmodel.getName());
	    productentity.setBrand(productmodel.getBrand());
	    productentity.setPrice(productmodel.getPrice());
	    productentity.setMadeIn(productmodel.getMadeIn());
	    productentity.setQuantity(productmodel.getQuantity());
	    productentity.setDiscountrate(productmodel.getDiscountrate());
	    productentity.setDiscount_price(discountprice);
	    productentity.setOfferPrice(offerprice);
	    productentity.setTaxRate(taxRate);
	    productentity.setFinalPrice(finalprice);
	    productentity.setStockValue(stockvalue);

	    productRepository.save(productentity);

	
 }
          public List<Productentity> getAllproducts(){
	  	List<Productentity>product=productRepository.findAll();
	    	return product;
	    	
	    }

		public Productentity serchbyid(Long id) {
			Optional<Productentity> optionaldata=productRepository.findById(id);
			
			if(optionaldata.isPresent()) {
				Productentity product=optionaldata.get();
				
			return product;
		}
			else {
				return null;
			}
		}
		public void deleteproductById(Long id) {

			productRepository.deleteById(id);
		}
   
		public ProductModel  editProductById(Long id) {
			Optional<Productentity> optionalData= productRepository.findById(id);
			if(optionalData.isPresent())
			{
				Productentity product = optionalData.get();
				
				ProductModel productModel = new ProductModel();
				productModel.setName(product.getName());
				productModel.setBrand(product.getBrand());
				productModel.setMadeIn(product.getMadeIn());
				productModel.setPrice(product.getPrice());
				productModel.setQuantity(product.getQuantity());
				productModel.setDiscountrate(product.getDiscountrate());
				return productModel;
			}
			else {
				return null;
			}
		}
		
		public void updateProductById(Long id, ProductModel updatedProductModel, Model model) {
		    Optional<Productentity> optionalData = productRepository.findById(id);
		    if (optionalData.isPresent()) {
		        Productentity product = optionalData.get();

		        // Update product entity with new values from the model
		        product.setName(updatedProductModel.getName());
		        product.setBrand(updatedProductModel.getBrand());
		        product.setMadeIn(updatedProductModel.getMadeIn());
		        product.setPrice(updatedProductModel.getPrice());
		        product.setQuantity(updatedProductModel.getQuantity());
		        product.setDiscountrate(updatedProductModel.getDiscountrate());

		        // Save the updated product
		        Productentity updatedProduct = productRepository.save(product);

		        // Map updated product to ProductModel (if needed for further use)
		        ProductModel productModel = new ProductModel();
		        productModel.setName(updatedProduct.getName());
		        productModel.setBrand(updatedProduct.getBrand());
		        productModel.setMadeIn(updatedProduct.getMadeIn());
		        productModel.setPrice(updatedProduct.getPrice());
		        productModel.setQuantity(updatedProduct.getQuantity());
		        productModel.setDiscountrate(updatedProduct.getDiscountrate());

		        // Add updated ProductModel to the Model for the view
		        model.addAttribute("updatedProduct", productModel);
		    } else {
		        // Handle the case when the product with the given ID does not exist
		        model.addAttribute("errorMessage", "Product not found with ID: " + id);
		    }
		}



			
		}
