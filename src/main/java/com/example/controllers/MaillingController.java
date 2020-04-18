package com.example.controllers;

import com.example.utils.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mail")
public class MaillingController {

    @Autowired
    private MailService mailService;


    @PostMapping("/contactMail")
    public void sendContactMail(@RequestBody String[] message){
        mailService.sendContactEmail(message);
    }
}