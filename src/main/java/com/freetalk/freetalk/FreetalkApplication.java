package com.freetalk.freetalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FreetalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreetalkApplication.class, args);
	}

}
