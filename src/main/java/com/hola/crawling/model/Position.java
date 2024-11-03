package com.hola.crawling.model;

import com.hola.crawling.entity.PostEntity;

import lombok.Builder;

@Builder
public record Position(
	Long id,
	String position,
	PostEntity postEntity
) {
}
