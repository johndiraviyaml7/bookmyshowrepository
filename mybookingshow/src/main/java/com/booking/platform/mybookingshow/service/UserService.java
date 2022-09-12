package com.booking.platform.mybookingshow.service;

import org.springframework.stereotype.Service;

import com.booking.platform.mybookingshow.models.AtomicPersistResponse;
import com.booking.platform.mybookingshow.models.JwtResponse;
import com.booking.platform.mybookingshow.models.LoginRequest;
import com.booking.platform.mybookingshow.models.SignupRequest;

@Service
public interface UserService {
	public AtomicPersistResponse saveUser(SignupRequest signUpRequest);
	public JwtResponse authenticateUser(LoginRequest loginRequest);

}
