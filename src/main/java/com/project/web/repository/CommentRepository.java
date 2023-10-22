package com.project.web.repository;

import com.project.web.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
