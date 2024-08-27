package com.sparta.springwork02.dto;

import com.sparta.springwork02.entity.Comment;
import com.sparta.springwork02.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String username;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private Schedule schedule;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().toString();
        this.modifiedAt = comment.getModifiedAt().toString();
        this.schedule = comment.getSchedule();
    }
}
