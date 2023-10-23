package com.project.web.sevice;

import com.project.web.dto.request.CommentRequest;
import com.project.web.dto.response.CommentResponse;
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
        commentRepository.save(request.toEntity(request));
        return true;
    }

    public List<CommentResponse> findAll() {

        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment: commentList) {
            CommentResponse commentResponse = new CommentResponse(
                    comment.getId(),
                    comment.getWriter(),
                    comment.getContents(),
                    comment.getCreatedTime(),
                    comment.getUpdatedTime()
            );
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    public CommentResponse findById(Long id) throws Exception {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("댓글을 찾을 수 없습니다."));
        return new CommentResponse(comment);
    }

    public boolean update(Long id, CommentRequest request) throws Exception {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new Exception("댓글을 찾을 수 없습니다."));

        comment.updateComment(request.getWriter(),
                request.getContents());

        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteById(Long id) {
        commentRepository.deleteById(id);
        return true;
    }



//    public Long save(CommentDTO commentDTO) {
//        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
//        if (optionalBoardEntity.isPresent()) {
//            BoardEntity boardEntity = optionalBoardEntity.get();
//            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
//            return commentRepository.save(commentEntity).getId();
//        } else {
//            return null;
//        }
//    }
//
//    public List<CommentDTO> findAll(Long boardId) {
//        BoardEntity boardEntity = boardRepository.findById(boardId).get();
//        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
//        List<CommentDTO> commentDTOList = new ArrayList<>();
//        for (CommentEntity commentEntity: commentEntityList) {
//            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
//            commentDTOList.add(commentDTO);
//        }
//        return commentDTOList;
//    }
}
