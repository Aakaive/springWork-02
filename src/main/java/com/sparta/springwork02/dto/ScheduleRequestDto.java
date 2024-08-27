package com.sparta.springwork02.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    private String username;
    private String title;
    private String description;
}
