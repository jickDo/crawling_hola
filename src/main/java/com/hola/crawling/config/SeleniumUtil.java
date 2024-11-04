package com.hola.crawling.config;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SeleniumUtil {
	public static void waitForElement(WebDriver driver, By locator, Duration timeout) {
		new WebDriverWait(driver, timeout)
			.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void safeClick(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			log.error("Click failed", e);
		}
	}

	public static void waitForClickable(WebDriver driver, By locator, Duration timeout) {
		new WebDriverWait(driver, timeout)
			.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void scrollIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor)driver).executeScript(
			"arguments[0].scrollIntoView(true);", element);
	}
}
