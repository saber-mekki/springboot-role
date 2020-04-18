package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.entities.volunteer;



public interface VolunteerReprository extends JpaRepository<volunteer, Long> {

    @Query("SELECT v FROM volunteer v WHERE v.accepted LIKE 'waitting'")
    List<volunteer> findNotAccepted();

    @Query("SELECT v FROM volunteer v WHERE v.accepted LIKE 'accepted'")
    List<volunteer> findAccepted();

}
