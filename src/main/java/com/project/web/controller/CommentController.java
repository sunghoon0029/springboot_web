package com.project.web.controller;

import com.project.web.dto.request.CommentRequest;
import com.project.web.dto.response.CommentResponse;
import com.project.web.sevice.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody CommentRequest request) throws Exception {
        return new ResponseEntity<>(commentService.save(request), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<CommentResponse> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/board/{id}")
    public List<CommentResponse> findAllByBoard(@PathVariable Long id) {
        return commentService.findAllByBoard(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody CommentRequest request) throws Exception {
        return new ResponseEntity<>(commentService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(commentService.deleteById(id), HttpStatus.OK);
    }


//    // ThymeLeaf
//    @PostMapping("/save")
//    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
//        System.out.println("commentDTO = " + commentDTO);
//        Long saveResult = commentService.save(commentDTO);
//        if (saveResult != null) {
//            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
//            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }
//    }
}
