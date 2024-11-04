package com.hola.crawling.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebDriverCleanup implements DisposableBean {

	private final WebDriver webDriver;

	@Override
	public void destroy() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
