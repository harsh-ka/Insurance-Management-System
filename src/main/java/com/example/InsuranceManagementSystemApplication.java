package com.example;

import com.example.dao.AdminRepository;
import com.example.dao.UserRepository;
import com.example.models.Admin;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.UUID;

@SpringBootApplication
public class InsuranceManagementSystemApplication{


	public static void main(String[] args)
	{
		SpringApplication.run(InsuranceManagementSystemApplication.class, args);
	}



}
