package com.example.services;

import java.util.List;

import com.example.entities.Statistique;
import com.example.entities.kadya;
import com.example.repositories.StatistiqueRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "StatistiqueService")
public class ServiceStatistiqueImpl implements ServiceStatistique{

    @Autowired
    private StatistiqueRepository repoStat;

    @Override
    public void ajouter(Statistique stat){
        repoStat.deleteAll();
        repoStat.save(stat);
    }


    @Override
    public List<Statistique> get(){
        return repoStat.findAll();
    }


}