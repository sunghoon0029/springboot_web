package com.project.web.sevice;

import com.project.web.dto.request.BoardRequest;
import com.project.web.dto.response.BoardResponse;
import com.project.web.entity.Board;
import com.project.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public boolean save(BoardRequest request) {
        boardRepository.save(request.toEntity(request));
        return true;
    }

    public boolean update(@PathVariable Long id,  BoardRequest request) throws Exception{
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다"));

        board.updateBoard(request.getWriter()
        ,request.getTitle()
        ,request.getContents());

        try {
            boardRepository.save(board);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<BoardResponse> findAll() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardResponse> boardResponseList = new ArrayList<>();

        for (Board board: boardList) {
            BoardResponse boardResponse = new BoardResponse(
                    board.getId(),
                    board.getWriter(),
                    board.getTitle(),
                    board.getContents(),
                    board.getCreatedTime(),
                    board.getUpdatedTime()
            );
            boardResponseList.add(boardResponse);
        }
        return boardResponseList;
    }

    public BoardResponse findById(Long id) throws Exception{
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다."));
        return new BoardResponse(board);
    }

    public boolean deleteById(Long id) {
        boardRepository.deleteById(id);
        return true;
    }
}
