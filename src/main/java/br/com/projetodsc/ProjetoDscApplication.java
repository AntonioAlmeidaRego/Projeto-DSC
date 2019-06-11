package br.com.projetodsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjetoDscApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoDscApplication.class, args);
	}	 
}
