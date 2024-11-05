package com.hola.crawling.config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

	@Bean
	public WebDriver webDriver() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--lang=ko");

		try {
			return new ChromeDriver(options);
		} catch (Exception e) {
			throw new RuntimeException("WebDriver 초기화 실패", e);
		}
	}

	@Bean
	public WebDriverWait webDriverWait(WebDriver webDriver) {
		return new WebDriverWait(webDriver, Duration.ofSeconds(10));
	}
}
