package com.hola.crawling.model;

import lombok.Builder;

@Builder
public record Post(
	Long id,
	String externalId
) {
}
