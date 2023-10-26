package com.project.web.sevice;

import com.project.web.dto.request.CommentRequest;
import com.project.web.dto.response.CommentResponse;
import com.project.web.entity.Board;
import com.project.web.entity.Comment;
import com.project.web.repository.BoardRepository;
import com.project.web.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public boolean save(CommentRequest request) {
        Board board = boardRepository.findById(request.getBoard()).get();
        Comment comment = request.toSaveEntity(request, board);

        commentRepository.save(comment);

        return true;
    }

    public List<CommentResponse> findAll() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment: commentList) {
            commentResponseList.add(CommentResponse.toDTO(comment));
        }
        return commentResponseList;
    }

    public CommentResponse findById(Long id) throws Exception {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("댓글을 찾을 수 없습니다."));

        return new CommentResponse(comment);
    }

    public List<CommentResponse> findAllByBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        List<Comment> commentList = commentRepository.findAllByBoardOrderByIdDesc(board);
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment: commentList) {
            CommentResponse commentResponse = CommentResponse.toDTO(comment, id);
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    public boolean update(Long id, CommentRequest request) throws Exception {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("댓글을 찾을 수 없습니다."));

        comment.updateComment(request.getWriter(),
                request.getContents());

        commentRepository.save(comment);

        return true;
    }

    public boolean deleteById(Long id) {
        commentRepository.deleteById(id);

        return true;
    }
}
