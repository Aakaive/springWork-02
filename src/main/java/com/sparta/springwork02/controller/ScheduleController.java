package com.sparta.springwork02.controller;

import com.sparta.springwork02.dto.ScheduleRequestDto;
import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){
        return scheduleService.findAllSchedule();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto findScheduleById(@PathVariable("id") Long id){
        return scheduleService.findScheduleById(id);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto responseDto = scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Long updateSchedule(@PathVariable("id") Long id, @RequestBody ScheduleRequestDto requestDto){
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable("id") Long id){
        scheduleService.deleteSchedule(id);
    }
}
