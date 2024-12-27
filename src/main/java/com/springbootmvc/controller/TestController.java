package com.springbootmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

@GetMapping("/pincodedetails/{pincode}")

public ResponseEntity<String> getpincodeDetails(@PathVariable("pincode")String pincode)
{
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String>responce=restTemplate.getForEntity("https://api.postalpincode.in/pincode/"+pincode, String.class);
	return ResponseEntity.status(HttpStatus.OK).header("status","Data read success").body(responce.getBody());
}
	

}
