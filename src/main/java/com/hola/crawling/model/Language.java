package com.hola.crawling.model;

import lombok.Builder;

@Builder
public record Language(
	Long id,
	String language) {
}
