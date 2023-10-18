package com.codereview.codereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodereviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodereviewApplication.class, args);
	}

}
