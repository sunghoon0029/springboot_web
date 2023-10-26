package com.project.web.dto.request;

import com.project.web.entity.Board;
import com.project.web.entity.Member;
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

    private Long member;

    public Board toSaveEntity(BoardRequest request, Member member) {
        return Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .contents(contents)
                .member(member)
                .build();
    }

    public Board toSaveFileEntity(BoardRequest request, Member member) {
        return Board.builder()
                .writer(writer)
                .title(title)
                .contents(contents)
                .hits(0)
                .fileAttached(1)
                .member(member)
                .build();
    }
}
