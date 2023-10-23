package com.project.web.dto.response;

import com.project.web.entity.Board;
import com.project.web.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String writer;

    private String contents;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.contents = comment.getContents();
        this.createdTime = comment.getCreatedTime();
        this.updatedTime = comment.getUpdatedTime();
    }
}
