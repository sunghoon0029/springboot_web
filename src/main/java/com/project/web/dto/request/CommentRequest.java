package com.project.web.dto.request;

import com.project.web.entity.Board;
import com.project.web.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long id;

    private String writer;

    private String contents;

    public static Comment toEntity(CommentRequest request) {
        return Comment.builder()
                .id(request.getId())
                .writer(request.getWriter())
                .contents(request.getContents())
                .build();
    }
}
