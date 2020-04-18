package com.example.services;

import java.util.List;
import com.example.entities.volunteer;

public interface ServiceVolunteer {
	public List<volunteer> getall();
	public void register(volunteer u);
	public void delete(long id);
	public void  acceptVolunteer(long id);
	public List<volunteer> getNonAccepted();

}
