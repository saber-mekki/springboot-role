package com.example.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.User;
import com.example.services.IServiceUser;
import com.example.utils.FileStorageService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IServiceUser userService;
	
	@Autowired
	private FileStorageService fs;


    @GetMapping(value="/getall")
    public List<User> listUser(){
        return userService.getAll();
    }


    @GetMapping(value = "/getone/{id}")
    public User getOne(@PathVariable(value = "id") int id){
        return userService.findById(id);
    }
    

    @PostMapping("/update/{user_id}")	
    public @ResponseBody void update(@PathVariable int user_id,@RequestBody  User user) {
    	Optional<User> result=(Optional<User>) userService.findUser(user_id);
    userService.updateUser(user, result);
    
    }

    @GetMapping(value = "/photo/{id}")
    public Resource getPhoto(@PathVariable int id) throws IOException {
    	String fileName = userService.getFileName(id);
    	return fs.loadFileAsResource(fileName);

    }
    
    @PostMapping(value="/upload-image", consumes =  {"multipart/form-data"})
    public String uploadImage(@RequestParam("file")  MultipartFile file) {
    	return userService.uploadImage(file);
    }

}
