package com.booking.platform.mybookingshow.service;

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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.booking.platform.mybookingshow.entity.BkpRole;
import com.booking.platform.mybookingshow.entity.BkpUser;
import com.booking.platform.mybookingshow.entity.ERole;
import com.booking.platform.mybookingshow.models.MessageResponse;
import com.booking.platform.mybookingshow.models.AtomicPersistResponse;
import com.booking.platform.mybookingshow.models.JwtResponse;
import com.booking.platform.mybookingshow.models.LoginRequest;
import com.booking.platform.mybookingshow.models.SignupRequest;
import com.booking.platform.mybookingshow.repository.RoleRepository;
import com.booking.platform.mybookingshow.repository.UserRepository;
import com.booking.platform.mybookingshow.security.JwtHelper;
import com.booking.platform.mybookingshow.security.services.UserDetailsImpl;
import com.booking.platform.mybookingshow.security.services.UserDetailsServiceImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	JwtHelper jwtHelper;
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    PasswordEncoder encoder;
    
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;


	public AtomicPersistResponse saveUser(SignupRequest signUpRequest) {
		AtomicPersistResponse atomicPersistResponse=new AtomicPersistResponse();
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			atomicPersistResponse.setStatus(false);
			atomicPersistResponse.setMessage("Error: Username is already taken!");
			return atomicPersistResponse;
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			atomicPersistResponse.setStatus(false);
			atomicPersistResponse.setMessage("Error: Email is already in use!");
			return atomicPersistResponse;
		}

		// Create new user's account
		BkpUser bkpUser = new BkpUser(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<BkpRole> bkpRoles = new HashSet<>();

		if (strRoles == null) {
			BkpRole userRole = roleRepository.findByName(ERole.ROLE_CONSUMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			bkpRoles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					BkpRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					bkpRoles.add(adminRole);

					break;
				case "mod":
					BkpRole modRole = roleRepository.findByName(ERole.ROLE_THEATRE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					bkpRoles.add(modRole);

					break;
				default:
					BkpRole userRole = roleRepository.findByName(ERole.ROLE_CONSUMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					bkpRoles.add(userRole);
				}
			});
		}
		bkpUser.setFullName(signUpRequest.getFullName());
		bkpUser.setMobileNumber(signUpRequest.getMobileNumber());
		bkpUser.setCreatedDate(LocalDateTime.now() );
		bkpUser.setLastModifiedDate(LocalDateTime.now() );
		bkpUser.setRoles(bkpRoles);
		if(userRepository.save(bkpUser) != null)
		{atomicPersistResponse.setStatus(true);
		atomicPersistResponse.setMessage("User registered successfully!");}
		return atomicPersistResponse;
	}
	
	public JwtResponse authenticateUser(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails;
	        try {
	            userDetails =(UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.getUsername());
	        } catch (UsernameNotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
	        }

	        if (encoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
	            Map<String, String> claims = new HashMap<>();
	            claims.put("username", loginRequest.getUsername());

	            String authorities = userDetails.getAuthorities().stream()
	                    .map(GrantedAuthority::getAuthority)
	                    .collect(Collectors.joining(" "));
	            claims.put("roles", authorities);
	            claims.put("userId", String.valueOf(1));
	        
	            String jwt = jwtHelper.createJwtForClaims(loginRequest.getUsername(), claims);
		//String jwt = jwtUtils.generateJwtToken(authentication);
	        
		//UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles);
	        }
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
	}

}
