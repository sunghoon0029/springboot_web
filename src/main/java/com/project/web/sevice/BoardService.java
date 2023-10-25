package com.project.web.sevice;

import com.project.web.dto.request.BoardRequest;
import com.project.web.dto.response.BoardResponse;
import com.project.web.entity.Board;
import com.project.web.entity.BoardFile;
import com.project.web.repository.BoardFileRepository;
import com.project.web.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public boolean save(BoardRequest request) throws IOException {

        if (request.getFile() == null) {

            Board board = request.toSaveEntity();
            boardRepository.save(board);
        } else {

            Board board = request.toSaveFileEntity();
            Long id = boardRepository.save(board).getId();
            Board getBoard = boardRepository.findById(id).get();

            for (MultipartFile file: request.getFile()) {

                String originalFileName = file.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
                String savePath = "/Users/josunghoon/springboot_img/" + storedFileName;
                file.transferTo(new File(savePath));

                BoardFile boardFile = BoardFile.builder()
                        .board(getBoard)
                        .originalFileName(originalFileName)
                        .storedFileName(storedFileName)
                        .build();

                boardFileRepository.save(boardFile);
            }
        }
        return true;
    }

    public List<BoardResponse> findAll() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardResponse> boardResponseList = new ArrayList<>();

        for (Board board: boardList) {

            boardResponseList.add(BoardResponse.toDTO(board));
        }
        return boardResponseList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardResponse findById(Long id) throws Exception {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다."));

        updateHits(id);

        return new BoardResponse(board);
    }

    public boolean update(Long id, BoardRequest request) throws Exception{
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception("게시글을 찾을 수 없습니다"));

        board.updateBoard(request.getWriter(),
                request.getTitle(),
                request.getContents());

        boardRepository.save(board);

        return true;
    }

    public boolean deleteById(Long id) {
        boardRepository.deleteById(id);
        return true;
    }
}
