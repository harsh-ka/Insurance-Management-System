package com.example.demo;

import com.example.dao.EmployeeRepository;
import com.example.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class InsuranceManagementSystemApplication{


	public static void main(String[] args)
	{
		SpringApplication.run(InsuranceManagementSystemApplication.class, args);

	}
}
