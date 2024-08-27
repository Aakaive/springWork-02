package com.sparta.springwork02.service;

import com.sparta.springwork02.dto.CommentRequestDto;
import com.sparta.springwork02.dto.CommentResponseDto;
import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.entity.Comment;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.repository.CommentRepository;
import com.sparta.springwork02.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    public CommentResponseDto saveSchedule(Long id, CommentRequestDto requestDto) {
        scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Comment comment = new Comment();
        comment.setSchedule(scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Schedule not found")));
        comment.setContent(requestDto.getContent());
        comment.setUsername(requestDto.getUsername());
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> findAllCommentBySchedule(Long id) {
        scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().filter(comment -> comment.getSchedule().getId() == id)
                .map(comment -> new CommentResponseDto(comment))
                .collect(Collectors.toList());
    }

    public CommentResponseDto findCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        return new CommentResponseDto(comment);
    }

    public Long updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setContent(requestDto.getContent());
        comment.setUsername(requestDto.getUsername());
        commentRepository.save(comment);
        return comment.getId();
    }

    public ResponseEntity<Void> deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    public List<CommentResponseDto> findAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> new CommentResponseDto(comment)).collect(Collectors.toList());
    }
}
