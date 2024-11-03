package com.hola.crawling.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	Long id;

	@Column(name = "external_id", unique = true)
	@EqualsAndHashCode.Include
	String externalId;

	@Column(name = "language")
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Language> languages = new ArrayList<>();

	@Column(name = "position")
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Position> positions = new ArrayList<>();

	boolean isClosed;

	@Column
	LocalDateTime startDate;

	@Column
	LocalDateTime endDate;

	@Column
	String type;

	@Column
	String onlineOrOffline;

	@Column
	String contactType;

	@Column
	String expectedPeriod;

	@Column
	String title;

	@Column
	LocalDateTime createdAt;

	@Column
	String state;
}
