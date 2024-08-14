package com.amagana.technicaltest.employeemanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Open Api definition", version = "3.0", description = "Description Open API Definition"))
public class EmployeemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementApplication.class, args);
		Set<String> setString = new HashSet<>();
		setString.add("evann");
		setString.add("noe");
		setString.add("doc");
		System.out.println(setString);
		setString.remove("evann");
		System.out.println(setString);
	}

}
