package com.sparta.springwork02.dto;

import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OneScheduleResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long commentCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long createdById;
    private List<User> users;

    public OneScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.commentCnt = (long) schedule.getComments().size();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.createdById = schedule.getCreatedBy().getId();
        this.users = schedule.getUserSchedules().stream()
                .map(usc -> usc.getUser()).collect(Collectors.toList());
    }
}
