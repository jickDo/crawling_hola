package com.hola.crawling.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

	public List<String> findNonDuplicateExternalIds(List<String> externalIds) {
		Set<String> existingIds = new HashSet<>(postRepository.findExistingExternalIds(externalIds));
		return externalIds.stream()
			.filter(id -> !existingIds.contains(id))
			.toList();
	}

	public void savePosts(List<String> externalIds) {
		List<PostEntity> newPosts = externalIds.stream()
			.map(id -> PostEntity.builder()
				.externalId(id)
				.build())
			.collect(Collectors.toList());

		postRepository.saveAll(newPosts);
	}
}
