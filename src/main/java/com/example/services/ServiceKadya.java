package com.example.services;

import java.util.List;

import com.example.entities.kadya;

public interface  ServiceKadya {
	
public List<kadya> getall();
public void register(kadya u);
public void delete(long id);
}
