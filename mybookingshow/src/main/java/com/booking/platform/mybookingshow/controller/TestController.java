package com.booking.platform.mybookingshow.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	  @ApiOperation(value = "This method is used to get the clients.")
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@ApiOperation(value = "This method is used to get the clients.")
	@GetMapping("/user")
	@PreAuthorize("hasRole('CONSUMER') or hasRole('THEATRE_ADMIN') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	  @ApiOperation(value = "This method is used to get the clients.")
	@GetMapping("/mod")
	@PreAuthorize("hasRole('THEATRE_ADMIN')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
