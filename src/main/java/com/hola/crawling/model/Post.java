package com.hola.crawling.model;

import java.time.LocalDateTime;
import java.util.List;

public record Post(
	Long id,
	String externalId,
	List<Language> languages,
	List<Position> positions,
	boolean isClosed,
	LocalDateTime startDate,
	LocalDateTime endDate,
	String type,
	String onlineOrOffline,
	String contactType,
	String expectedPeriod,
	String title,
	LocalDateTime createdAt,
	String state
) {
}
