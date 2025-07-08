package com.crypt.SpringCoreDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages= {"com.crypt.SpringCoreDemo", "com.crypt.util"})
@SpringBootApplication
public class SpringCoreDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}
}
