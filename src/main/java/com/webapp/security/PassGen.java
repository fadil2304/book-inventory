package com.webapp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	}

	public static String generatePassword(String rawText) throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawText);
	}
}
