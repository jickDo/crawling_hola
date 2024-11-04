package com.hola.crawling.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/hola")
	public void crawlHola() {
		log.info("크롤링 시작");
		facadeService.crawlAndSave();
	}
}
