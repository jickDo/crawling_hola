package com.hola.crawling.model;

import com.hola.crawling.entity.PostEntity;

import lombok.Builder;

@Builder
public record Language(
	Long id,
	String language,
	PostEntity postEntity
) {
}
