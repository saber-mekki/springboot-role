package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.entities.volunteer;
import com.example.repositories.VolunteerReprository;
import com.example.utils.MailService;

@Service(value = "VolenteerService")
public class ServiceVolunteerImpl implements ServiceVolunteer{
	@Autowired
	 private VolunteerReprository  volunteerReprository;

	@Autowired
	private MailService mailService;

	
	
/* ******* */
	@Override
	public void acceptVolunteer(long id){
		volunteer v=volunteerReprository.findById(id).get();
		if(v !=null){
			v.setAccepted("accepted");
			volunteerReprository.save(v);
		}
	}

	@Override
	public void delete(long id) {
		volunteerReprository.deleteById(id);
	}
	
	@Override
	public List<volunteer> getall(){
		return volunteerReprository.findAccepted();
	};
	

	@Override
	public void register(volunteer Volunteer) {
		mailService.sendVolunteerEmail(Volunteer);
		Volunteer.setAccepted("waitting");
		volunteerReprository.save(Volunteer);
	}

	@Override
	public List<volunteer> getNonAccepted(){
		return volunteerReprository.findNotAccepted();
	}

}
