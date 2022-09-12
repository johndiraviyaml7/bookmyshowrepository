package com.booking.platform.mybookingshow.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.booking.platform.mybookingshow.entity.ERole;
import com.booking.platform.mybookingshow.entity.BkpRole;
import com.booking.platform.mybookingshow.entity.BkpUser;
import com.booking.platform.mybookingshow.models.AtomicPersistResponse;
import com.booking.platform.mybookingshow.models.JwtResponse;
import com.booking.platform.mybookingshow.models.LoginRequest;
import com.booking.platform.mybookingshow.models.MessageResponse;
import com.booking.platform.mybookingshow.models.SignupRequest;
import com.booking.platform.mybookingshow.repository.RoleRepository;
import com.booking.platform.mybookingshow.repository.UserRepository;
import com.booking.platform.mybookingshow.security.JwtHelper;
import com.booking.platform.mybookingshow.security.services.UserDetailsImpl;
import com.booking.platform.mybookingshow.security.services.UserDetailsServiceImpl;
import com.booking.platform.mybookingshow.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	UserService userService; 


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {		

		return ResponseEntity.ok(userService.authenticateUser(loginRequest));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		AtomicPersistResponse atomicPersistResponse=userService.saveUser(signUpRequest);
		if (atomicPersistResponse!=null && atomicPersistResponse.isStatus()==false) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(atomicPersistResponse.isMessage()));
		} 
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
