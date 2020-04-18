package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.entities.volunteer;
import com.example.services.ServiceVolunteer;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/volunteer")
public class volunteerController {
	@Autowired
	private ServiceVolunteer serviceVolunteer;
	
	@GetMapping("/all")	  
	public List<volunteer> getall(){
		return serviceVolunteer.getall();
	}
	@PostMapping("/save")
	public void save(@RequestBody volunteer volunteer){
		serviceVolunteer.register(volunteer);
		 
	}
	
	
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable (value = "id") long id){
	serviceVolunteer.delete(id);
			}

	@GetMapping("/nonAcceptedYet")	  
	public List<volunteer> getNonAcceptedYet(){
		return serviceVolunteer.getNonAccepted();
	}

	@GetMapping("/accept/{id}")	  
	public void acceptVolunteer(@PathVariable (value = "id") long id){
		serviceVolunteer.acceptVolunteer(id);
	}

}
