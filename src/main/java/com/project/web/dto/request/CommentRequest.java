package com.project.web.dto.request;

import com.project.web.entity.Board;
import com.project.web.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequest {

    private Long id;

    private String writer;

    private String contents;

    private Long board;

    public Comment toSaveEntity(CommentRequest request, Board board) {
        return Comment.builder()
                .id(request.id)
                .writer(request.writer)
                .contents(request.contents)
                .board(board)
                .build();
    }
}
