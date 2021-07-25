package com.victor.springapi.config;

import com.victor.springapi.services.DBService;
import com.victor.springapi.services.EmailService;
import com.victor.springapi.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig
{
	@Autowired
	private DBService dbService;

	@Bean
	public Boolean instantiateDatabase() throws ParseException
	{
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService()
	{
		return new MockEmailService();
//		return new SMTPEmailService();
	}
}
