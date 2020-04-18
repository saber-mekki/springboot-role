package com.example.utils;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.entities.ConfirmationToken;
import com.example.entities.User;
import com.example.entities.volunteer;

@Transactional
@Service("mailService")
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendResetEmail(User user,ConfirmationToken confirmationToken) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Reset password");
		mail.setText("To reset your password, please click here:"
				+ "http://localhost:4200/auth/reset-password?token="+confirmationToken.getConfirmationToken());
		javaMailSender.send(mail);
		
	}
	
	public void sendConfirmationEmail(User user, ConfirmationToken confirmationToken) throws MailException {
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:4200/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
        javaMailSender.send(mailMessage);		
	}


	public void sendVolunteerEmail(volunteer volt) throws MailException {
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("saber.mekki6@gmail.com");
        mailMessage.setSubject("Demande de bénévole");
		mailMessage.setText("Je suis : \n Nom : "+ volt.getName()+
		"\n Adresse : "+volt.getAdresse()+
		"\n Numéro : "+volt.getNumero()+
		"\n Corps : "+volt.getCorps()+
		"Je veux  etre bénévole dans vos activités");
        javaMailSender.send(mailMessage);		
	}

	public void sendContactEmail(String[] message) throws MailException {
		
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("saber.mekki6@gmail.com");
        mailMessage.setSubject(message[2]);
		mailMessage.setText(message[3]+
		"\n envoyé de la part de :"+ message[0] + "  -- Email : "+message[1]);
        javaMailSender.send(mailMessage);		
	}



}