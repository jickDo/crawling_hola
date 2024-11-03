package com.hola.crawling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hola.crawling.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
