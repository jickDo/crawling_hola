package com.hola.crawling.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hola.crawling.service.FacadeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/crawling")
@RequiredArgsConstructor
public class CrawlingController {

	private final FacadeService facadeService;

	@Value("${api.key}")
	private String apiKey;

	@GetMapping("/hola")
	public ResponseEntity<?> crawlHola(
		@RequestHeader(value = "X-API-KEY", required = true) String receivedApiKey) {

		if (!apiKey.equals(receivedApiKey)) {
			log.warn("잘못된 API 키 요청 감지");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		log.info("크롤링 시작");
		facadeService.crawlAndSave();
		return ResponseEntity.ok().build();
	}
}
