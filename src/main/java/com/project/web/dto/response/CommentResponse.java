package com.project.web.dto.response;

import com.project.web.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String writer;

    private String contents;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Long board;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.contents = comment.getContents();
        this.createdTime = comment.getCreatedTime();
        this.updatedTime = comment.getUpdatedTime();
    }

    public static CommentResponse toDTO(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setWriter(comment.getWriter());
        commentResponse.setContents(comment.getContents());
        commentResponse.setCreatedTime(comment.getCreatedTime());
        commentResponse.setUpdatedTime(comment.getUpdatedTime());
        return commentResponse;
    }

    public static CommentResponse toDTO(Comment comment, Long id) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setWriter(comment.getWriter());
        commentResponse.setContents(comment.getContents());
        commentResponse.setCreatedTime(comment.getCreatedTime());
        commentResponse.setUpdatedTime(comment.getUpdatedTime());
        commentResponse.setBoard(id);
        return commentResponse;
    }
}
