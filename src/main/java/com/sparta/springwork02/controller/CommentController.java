package com.sparta.springwork02.controller;

import com.sparta.springwork02.dto.CommentRequestDto;
import com.sparta.springwork02.dto.CommentResponseDto;
import com.sparta.springwork02.dto.ScheduleRequestDto;
import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.service.CommentService;
import com.sparta.springwork02.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponseDto> findAllComments() {
        return commentService.findAllComment();
    }

    @GetMapping("/schedules/{id}/comments")
    public List<CommentResponseDto> findAllCommentsBySchedule(@PathVariable("id") Long id){
        return commentService.findAllCommentBySchedule(id);
    }

    @GetMapping("/comments/{id}")
    public CommentResponseDto findCommentById(@PathVariable("id") Long id){
        return commentService.findCommentById(id);
    }

    @PostMapping("/schedules/{id}/comment")
    public ResponseEntity<CommentResponseDto> saveComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto){
        CommentResponseDto responseDto = commentService.saveSchedule(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public Long updateSchedule(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto){
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
    }
}
