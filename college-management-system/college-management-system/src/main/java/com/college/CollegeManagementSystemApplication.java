package com.college;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching
public class CollegeManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(CollegeManagementSystemApplication.class, args);
	}
}
