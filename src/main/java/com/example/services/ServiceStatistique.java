package com.example.services;

import java.util.List;

import com.example.entities.Statistique;


public interface ServiceStatistique{
    public void ajouter(Statistique stat);

    public List<Statistique> get();

}