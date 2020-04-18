package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.kadya;
import com.example.repositories.kadyaReprository;

@Service(value = "kadyaService")
public class ServiceKadyaImp implements ServiceKadya{
	@Autowired
	private kadyaReprository   adyaReprository;
	
	
	@Override
	public void register(kadya kadya) {
		adyaReprository.save(kadya);
	}

	@Override
	public List<kadya> getall(){
		List<kadya> list=new ArrayList<>();
		adyaReprository.findAll().iterator().forEachRemaining(list::add);
		return list;
	};

	@Override
	public void delete(long id){
		adyaReprository.deleteById(id);
	}

}
