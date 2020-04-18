package com.example.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.configSecurity.TokenProvider;
import com.example.entities.ConfirmationToken;

import com.example.entities.User;
import com.example.repositories.ConfirmationTokenRepository;
import com.example.services.IServiceUser;
import com.example.utils.AuthToken;
import com.example.utils.MailService;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	 private AuthenticationManager authenticationManager;

	 @Autowired
	 private TokenProvider jwtTokenUtil;

	 @Autowired
	 private IServiceUser userService;
	 
	 @Autowired
	 private ConfirmationTokenRepository confirmationTokenRepository; 
	 
	 @Autowired
	 private MailService mailService;
	    
	   

	    @PostMapping(value = "/login")
	    public ResponseEntity<?> register(@RequestBody User loginUser) throws AuthenticationException {

	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getEmail(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final String token = jwtTokenUtil.generateToken(authentication);
	        return ResponseEntity.ok(new AuthToken(token));
	    }
	    
	    @PostMapping(value="/register")
	    public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
	    	
	    	user.setEnabled(false);
	    	userService.register(user);
	    	
	    	ConfirmationToken token= confirmationTokenRepository.findByUser(user);
	    	if(token !=null) {
	    		return new ResponseEntity<String>(" This email already exist \n "
	    				+ "Please check your mail to confirm your account", HttpStatus.EXPECTATION_FAILED);
	    	}
	    	
	    	ConfirmationToken confirmationToken=new ConfirmationToken(user, "confirmation-account");
	    	confirmationTokenRepository.save(confirmationToken);
	    	mailService.sendConfirmationEmail(user, confirmationToken);
	    	
	        return  new ResponseEntity<String>("Congratulations! Your account has been created"
	        		+ "Please check your email to continue the registration ", HttpStatus.ACCEPTED);
	    }
	    
	    
	    
	    @GetMapping(value = "/confirm-account")
	    public boolean confirmUserAccount(@RequestParam("token")String confirmationToken)
	    {
			ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	        if(token != null && token.getType().equals("confirmation-account"))
	        {
	            User user = userService.findByEmail(token.getUser().getEmail());
	            if (user.isEnabled()) return true;
	            
	            else{
	            	user.setEnabled(true);
					userService.register(user);
					confirmationTokenRepository.delete(token);
	            	return true;}
	        }
	        else
	        {
	        	return false;
	        }
	     }
	    
	    
	    @PostMapping(value="/upload-image", consumes =  {"multipart/form-data"})
	    public String uploadImage(@RequestParam("file")  MultipartFile file) {
	    	return userService.uploadImage(file);
	    }
	    
	    //tape your email page --> send mail
	    @PostMapping(value="/forgot-password")
	    public ResponseEntity<?> resetMail(@RequestBody String email){
	    	User user= userService.findByEmail(email);
	    	if(user!= null) {
	    		ConfirmationToken confirmationToken = new ConfirmationToken(user, "reset-password");
		        confirmationTokenRepository.save(confirmationToken);
		        mailService.sendResetEmail(user,confirmationToken);
		        
		        return new ResponseEntity<String>("An email has been sent to your address mail"
		        	+ "Please check your email to reset your password",HttpStatus.OK);
	    	}
	    	else return new ResponseEntity<>("This mail doesn't exist!!",HttpStatus.EXPECTATION_FAILED);
	    }
	    
	    //after click on mail link
	    @GetMapping(value="/reset-password")
    	public boolean newPassword(@RequestParam("token") String confirmationToken)
    	{
	    	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
	        if(token != null && token.getType().equals("reset-password")){
	        	return true;
	        }
	        else return false;   
    	}
	    
	    //reset password button (save new password)
	    @PostMapping(value="/reset-password")
    	public ResponseEntity<?> newPassword(@RequestParam("token") String confirmationToken,
    			                             @RequestBody String password){
	    	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	        if(token != null && token.getType().equals("reset-password")){
	        	User user =token.getUser();
	        	user.setPassword(password);
	        	userService.register(user);
    	return new ResponseEntity<>("Password is reset \n"
    			+ "You can now login with your new password",HttpStatus.OK);}
	        else return new ResponseEntity<>("Password can't be reset \n The link is invalid or broken!",HttpStatus.EXPECTATION_FAILED);
    	}
	    


}
