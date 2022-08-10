package com.sparta.hanghae5.repository;

import com.sparta.hanghae5.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
