package com.hola.crawling.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.hola.crawling.config.SeleniumUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingService {

	private final WebDriver webDriver;
	private static final int MAX_PAGES = 5;
	private static final Duration TIMEOUT = Duration.ofSeconds(10);

	private static final String CRAWLING_HOLA = "https://holaworld.io";

	public List<String> crawlHola() {
		List<String> studyIds = new ArrayList<>();
		try {
			webDriver.get(CRAWLING_HOLA);
			setupFilters();
			processPagination(studyIds);
		} catch (Exception e) {
			log.error("크롤링 중 오류 발생", e);
		}

		return studyIds.stream()
			.distinct()
			.collect(Collectors.toList());
	}

	private void setupFilters() {
		try {
			clickPositionFilter();
			selectBackendOption();
		} catch (Exception e) {
			log.error("필터 설정 중 오류 발생", e);
			throw new RuntimeException("필터 설정 실패", e);
		}
	}

	private void clickPositionFilter() throws InterruptedException {
		By filterLocator = By.cssSelector(".css-ohollo-control");
		WebElement filterButton = waitAndFindElement(filterLocator);
		SeleniumUtil.safeClick(filterButton);
		Thread.sleep(1000);
	}

	private void selectBackendOption() throws InterruptedException {
		By backendLocator = By.id("react-select-2-option-2");
		WebElement backendOption = waitAndFindElement(backendLocator);
		SeleniumUtil.safeClick(backendOption);
		Thread.sleep(1000);
	}

	private void processPagination(List<String> studyIds) {
		try {
			List<WebElement> paginationButtons = getPaginationButtons();
			int totalPages = Math.min(MAX_PAGES, paginationButtons.size());
			log.info("크롤링 시작: 총 {}페이지 수집 예정", totalPages);

			for (int currentPage = 0; currentPage < totalPages; currentPage++) {
				processPage(currentPage, totalPages, studyIds);
				if (currentPage < totalPages - 1) {
					moveToNextPage(currentPage);
				}
			}

			log.info("크롤링 완료: 총 {}개의 스터디 ID 수집", studyIds.size());
		} catch (Exception e) {
			log.error("페이지네이션 처리 중 오류 발생", e);
		}
	}

	private List<WebElement> getPaginationButtons() {
		By paginationLocator = By.cssSelector(".MuiPagination-ul button");
		SeleniumUtil.waitForElement(webDriver, paginationLocator, TIMEOUT);
		return webDriver.findElements(paginationLocator);
	}

	private void processPage(int currentPage, int totalPages, List<String> studyIds) {
		try {
			log.info("현재 페이지: {}/{}", currentPage + 1, totalPages);
			List<WebElement> studyItems = getStudyItems();
			log.info("현재 페이지의 스터디 아이템 수: {}", studyItems.size());
			collectStudyIds(studyItems, studyIds);
		} catch (Exception e) {
			log.error("페이지 {} 처리 중 오류 발생", currentPage + 1, e);
		}
	}

	private List<WebElement> getStudyItems() {
		By studyListLocator = By.cssSelector(".studyList_studyList__3xoys a");
		SeleniumUtil.waitForElement(webDriver, studyListLocator, TIMEOUT);
		return webDriver.findElements(studyListLocator);
	}

	private void moveToNextPage(int currentPage) throws InterruptedException {
		List<WebElement> paginationButtons = getPaginationButtons();
		WebElement nextPageButton = paginationButtons.stream()
			.filter(button -> {
				String label = button.getAttribute("aria-label");
				return label != null && label.matches("Go to page .*");
			})
			.skip(currentPage)
			.findFirst()
			.orElseThrow(() -> new RuntimeException("다음 페이지 버튼을 찾을 수 없습니다."));

		SeleniumUtil.scrollIntoView(webDriver, nextPageButton);
		Thread.sleep(500);
		SeleniumUtil.safeClick(nextPageButton);
		Thread.sleep(1000);
	}

	private void collectStudyIds(List<WebElement> studyItems, List<String> externalIds) {
		for (WebElement item : studyItems) {
			try {
				String href = item.getAttribute("href");
				if (href != null && href.contains("/study/")) {
					String studyId = href.substring(href.lastIndexOf("/") + 1);
					externalIds.add(studyId);
					log.info("수집된 스터디 ID: {}", studyId);
				}
			} catch (Exception e) {
				log.error("스터디 ID 추출 중 오류 발생", e);
			}
		}
	}

	private WebElement waitAndFindElement(By locator) {
		SeleniumUtil.waitForClickable(webDriver, locator, TIMEOUT);
		return webDriver.findElement(locator);
	}
}