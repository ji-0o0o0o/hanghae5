package com.sparta.hanghae5.repository;

import com.sparta.hanghae5.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {
}
