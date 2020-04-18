package com.example.controllers;

import java.util.List;

import com.example.entities.Statistique;

import com.example.services.ServiceStatistique;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statistique")
public class StatistiqueController {


    @Autowired
    private ServiceStatistique serviceStatistique;

    @PostMapping("/save")
	public void save(@RequestBody Statistique stat){
        serviceStatistique.ajouter(stat);
    }


    @GetMapping("/getValues")
    public List<Statistique> getStatistiques(){
      return serviceStatistique.get(); 
    }



}