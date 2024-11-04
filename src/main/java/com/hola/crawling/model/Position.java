package com.hola.crawling.model;

import lombok.Builder;

@Builder
public record Position(
	Long id,
	String position) {
}
