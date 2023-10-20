package com.project.web.dto.response;

import com.project.web.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long id;

    private String writer;

    private String title;

    private String contents;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdTime = board.getCreatedTime();
        this.updatedTime = board.getUpdatedTime();
    }
}
