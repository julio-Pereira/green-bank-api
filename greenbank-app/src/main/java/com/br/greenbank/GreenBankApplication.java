package com.br.greenbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.br.greenbank")
@EntityScan(basePackages = "com.br.greenbank")
public class GreenBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenBankApplication.class, args);
	}

}
