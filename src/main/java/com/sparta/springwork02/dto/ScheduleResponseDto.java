package com.sparta.springwork02.dto;

import com.sparta.springwork02.entity.Schedule;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long commentCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long createdById;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.commentCnt = (long) schedule.getComments().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.createdById = schedule.getCreatedBy().getId();
    }
}
