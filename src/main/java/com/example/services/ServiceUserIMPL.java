package com.example.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.example.utils.FileStorageService;


@Transactional
@Service(value = "userService")
public class ServiceUserIMPL implements UserDetailsService, IServiceUser{

	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private UserRepository userRepository;
	

	
	@Override
	public void updateUser(Optional<User> users,User user) {
		user.setUser(users.get());
		userRepository.saveAndFlush(user);
		
	}


	@Autowired
	private FileStorageService fileStorageService;
	

	@Override
	public void register(User u) {
	    u.setPassword(bcryptEncoder.encode(u.getPassword()));
		userRepository.saveAndFlush(u);

		
	}
	@Override
	public Optional<User> findUser(int id) {
	
		return userRepository.findById(id);
	}
	
	
	
	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);		
	}


	@Override
	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES );
	}
	
	/*private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		Role role = user.getRole();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		return authorities;
	}*/

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public String uploadImage(MultipartFile file) {
		return fileStorageService.storeFile(file);
	}
	
	@Override
    public String getFileName(int id) {
           User user =  userRepository.findById(id).get();
           return user.getPhoto();
    }
	@Override
	public void updateUser(User user, Optional<User> users) {
		// TODO Auto-generated method stub
		
	}

}
