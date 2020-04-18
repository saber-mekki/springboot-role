package com.example.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.kadya;
import com.example.services.ServiceKadya;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kadya")
public class KadyaContoller {
	@Autowired
	private ServiceKadya serviceKadya;
	
	
	@GetMapping("/all")	  
	public List<kadya> getall(){
		return serviceKadya.getall();
	}
	@PostMapping("/save")
	public void save(@RequestBody kadya kadya){
		serviceKadya.register(kadya);
		 
	}

	@GetMapping("/delete/{id}")	  
	public void delete(@PathVariable long id){
		serviceKadya.delete(id);
	}
}
