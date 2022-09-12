package com.booking.platform.mybookingshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;



@SpringBootApplication
@EnableEncryptableProperties
public class MyBookingShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookingShowApplication.class, args);
	}

	}