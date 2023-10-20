package com.project.web.controller;

import com.project.web.dto.request.BoardRequest;
import com.project.web.dto.response.BoardResponse;
import com.project.web.sevice.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody BoardRequest request) throws Exception {
        return new ResponseEntity<>(boardService.save(request), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody BoardRequest request) throws Exception {
        return new ResponseEntity<>(boardService.update(id, request), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<BoardResponse> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(boardService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(boardService.deleteById(id), HttpStatus.OK);
    }
}
