package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statistique")
public class Statistique {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int contamination;
    private int healing;
    private int analyse;
    private int death;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContamination() {
        return contamination;
    }

    public void setContamination(int contamination) {
        this.contamination = contamination;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getAnalyse() {
        return analyse;
    }

    public void setAnalyse(int analyse) {
        this.analyse = analyse;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }
    
    




}