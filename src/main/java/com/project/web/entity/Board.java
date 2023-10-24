package com.project.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String writer;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private int hits;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BoardFile> boardFileList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public void updateBoard(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
