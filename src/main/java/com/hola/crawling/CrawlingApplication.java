package com.hola.crawling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hola.crawling.service.FacadeService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrawlingApplication implements CommandLineRunner {

	private final FacadeService facadeService;

	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args)
			.close();
	}

	@Override
	public void run(String... args) {
		facadeService.crawlAndSave();
	}
}
