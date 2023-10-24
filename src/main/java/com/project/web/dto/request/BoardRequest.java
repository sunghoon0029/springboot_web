package com.project.web.dto.request;

import com.project.web.entity.Board;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class BoardRequest {

    private Long id;

    private String writer;

    private String title;

    private String contents;

    private List<MultipartFile> file;

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .contents(contents)
                .build();
    }

    public Board toSaveFileEntity() {
        return Board.builder()
                .writer(writer)
                .title(title)
                .contents(contents)
                .hits(0)
                .fileAttached(1)
                .build();
    }
}
