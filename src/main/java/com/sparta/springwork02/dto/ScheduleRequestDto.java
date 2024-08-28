package com.sparta.springwork02.dto;

import com.sparta.springwork02.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String description;
    private Long createdById;
    private List<Long> userIds;
}
