package com.project.web.dto.response;

import com.project.web.entity.Board;
import com.project.web.entity.BoardFile;
import com.project.web.entity.Comment;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long id;

    private String writer;

    private String title;

    private String contents;

    private int hits;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;


    private List<MultipartFile> file;

    private List<String> originalFileName;

    private List<String> storedFileName;

    private int fileAttached;


    private List<Comment> comments = new ArrayList<>();

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.writer = board.getWriter();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.hits = board.getHits();
        this.createdTime = board.getCreatedTime();
        this.updatedTime = board.getUpdatedTime();
        this.fileAttached = board.getFileAttached();
    }

    public BoardResponse(Long id, String writer, String title, int hits, LocalDateTime createdTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.hits = hits;
        this.createdTime = createdTime;
    }

    public static BoardResponse toDTO(Board board, List<Comment> comments) {
        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setId(board.getId());
        boardResponse.setWriter(board.getWriter());
        boardResponse.setTitle(board.getTitle());
        boardResponse.setContents(board.getContents());
        boardResponse.setHits(board.getHits());
        boardResponse.setCreatedTime(board.getCreatedTime());
        boardResponse.setUpdatedTime(board.getUpdatedTime());
        if (board.getFileAttached() == 0) {
            boardResponse.setFileAttached(board.getFileAttached());
        } else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            boardResponse.setFileAttached(board.getFileAttached());
            for (BoardFile boardFile: board.getBoardFiles()) {
                originalFileNameList.add(boardFile.getOriginalFileName());
                storedFileNameList.add(boardFile.getStoredFileName());
            }
            boardResponse.setOriginalFileName(originalFileNameList);
            boardResponse.setStoredFileName(storedFileNameList);
        }
        boardResponse.setComments(comments);
        return boardResponse;
    }
}
