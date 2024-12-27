package com.springbootmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootmvc.entity.Productentity;
import com.springbootmvc.model.ProductModel;
import com.springbootmvc.service.Productservice;

import lombok.Data;

@Data
@Controller
public class Productcontroller {
	@Autowired
	Productservice productservice;
	
@GetMapping("/productform")
	
	public String getting() {
		
		return "add-product";
	}
	
  @PostMapping("/saveproducts")
	public String saveproduct(ProductModel product)
	{
	  productservice.saveproductsdetails(product);
	  return "success";
	}
    @GetMapping("/getallproducts")
    public String getAllproducts(Model model) {
    	
    List<Productentity>products=productservice.getAllproducts();
    	
    	model.addAttribute("products",products);
        return "product-list";
    
    }
   
    @GetMapping("/search")
    public String gesearchform() {
        return "search-product";
    }
    
    @PostMapping("/searchById")
    public String SearchById(@RequestParam Long id,Model model) {
        Productentity product= productservice.serchbyid(id);
        model.addAttribute("product", product);
        
        return "search-product" ;
    }
    
    //delete operation
    
    @GetMapping("/delete/{id}")
    public String deleteproductById(@PathVariable Long id)
    {
    	productservice.deleteproductById(id);
   	
    	return "redirect:/getallproducts";
   }
    
    //edit and view
    @GetMapping("/edit/{id}")
	public String showEditProductPage(@PathVariable("id") Long id,Model model) {
	    ProductModel product = productservice.editProductById(id); 
	    model.addAttribute("product", product);
	    model.addAttribute("id", id);
	    return "edit-product";
	}
    @PostMapping("/saveproducts/{id}")
    public String updateProductById(
            @PathVariable("id") Long id,
            @ModelAttribute("productModel") ProductModel productModel,
            Model model) {
        // Pass the updated product data to the service layer
        productservice.updateProductById(id, productModel, model);
        return "redirect:/getallproducts";
    }



}

