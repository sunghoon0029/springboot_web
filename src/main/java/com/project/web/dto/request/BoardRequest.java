package com.project.web.dto.request;

import com.project.web.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {

    private Long id;

    private String writer;

    private String title;

    private String contents;

    public static Board toEntity(BoardRequest request) {
        return Board.builder()
                .id(request.getId())
                .writer(request.getWriter())
                .title(request.getTitle())
                .contents(request.getContents())
                .build();
    }
}
