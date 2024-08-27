package com.sparta.springwork02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.springwork02.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
