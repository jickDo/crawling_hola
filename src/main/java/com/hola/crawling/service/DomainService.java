package com.hola.crawling.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hola.crawling.entity.PostEntity;
import com.hola.crawling.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DomainService {

	private final PostRepository postRepository;

	public void saveAllPostsFromExternalIds(List<String> externalIds) {
		List<String> newExternalIds = externalIds.stream()
			.filter(id -> !postRepository.findExistingExternalIds(externalIds).contains(id))
			.toList();

		if (!newExternalIds.isEmpty()) {
			postRepository.saveAll(newExternalIds.stream()
				.map(id -> PostEntity.builder()
					.externalId(id)
					.build())
				.collect(Collectors.toList()));
		}
	}
}
