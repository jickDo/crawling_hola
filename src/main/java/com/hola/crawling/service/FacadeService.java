package com.hola.crawling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FacadeService {

	private final CrawlingService crawlingService;
	private final DomainService domainService;
	private final MailService mailService;

	public void crawlAndSave() {
		List<String> crawlingIds = crawlingService.crawlHola();

		List<String> nonDuplicatedIds = domainService.findNonDuplicateExternalIds(crawlingIds);

		mailService.notifyNewPosts(nonDuplicatedIds);

		domainService.savePosts(nonDuplicatedIds);
	}
}
