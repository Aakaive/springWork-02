package com.sparta.springwork02.service;

import com.sparta.springwork02.dto.ScheduleRequestDto;
import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);

        return schedules.map(schedule -> new ScheduleResponseDto(schedule));
    }

    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(schedule))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule();
        schedule.setUsername(requestDto.getUsername());
        schedule.setTitle(requestDto.getTitle());
        schedule.setDescription(requestDto.getDescription());

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule not found")
        );

        schedule.setUsername(requestDto.getUsername());
        schedule.setTitle(requestDto.getTitle());
        schedule.setDescription(requestDto.getDescription());

        return scheduleRepository.save(schedule).getId();
    }

    public ResponseEntity<Void> deleteSchedule(Long id) {
        try {
            scheduleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule not found")
        );
        return new ScheduleResponseDto(schedule);
    }
}
