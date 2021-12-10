package com.acn_project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import myapp.driver.Driver;
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		Driver.Initalize();
		SpringApplication.run(DemoApplication.class, args);
	}

}
