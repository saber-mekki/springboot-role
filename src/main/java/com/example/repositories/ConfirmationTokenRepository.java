package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ConfirmationToken;
import com.example.entities.User;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	ConfirmationToken findByUser(User user);
}
