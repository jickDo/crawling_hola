package com.hola.crawling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hola.crawling.service.FacadeService;

@SpringBootApplication
public class CrawlingApplication implements CommandLineRunner {

	@Autowired
	private FacadeService facadeService;

	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args)
			.close();
	}

	@Override
	public void run(String... args) {
		facadeService.crawlAndSave();
	}
}
