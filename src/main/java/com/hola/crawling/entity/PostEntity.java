package com.hola.crawling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	Long id;

	@Column(name = "external_id", unique = true)
	@EqualsAndHashCode.Include
	String externalId;

}
