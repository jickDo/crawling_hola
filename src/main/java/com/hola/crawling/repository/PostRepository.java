package com.hola.crawling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hola.crawling.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	@Query("""
		SELECT p.externalId 
		FROM PostEntity p 
		WHERE p.externalId IN :ids
		""")
	List<String> findExistingExternalIds(@Param("ids") List<String> externalIds);
}
