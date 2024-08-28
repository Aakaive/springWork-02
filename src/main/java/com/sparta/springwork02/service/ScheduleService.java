package com.sparta.springwork02.service;

import com.sparta.springwork02.dto.ScheduleRequestDto;
import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.entity.User;
import com.sparta.springwork02.entity.UserSchedule;
import com.sparta.springwork02.repository.ScheduleRepository;
import com.sparta.springwork02.repository.UserRepository;
import com.sparta.springwork02.repository.UserScheduleRepository;
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
    private final UserScheduleRepository userScheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserScheduleRepository userScheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userScheduleRepository = userScheduleRepository;
        this.userRepository = userRepository;
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
        schedule.setTitle(requestDto.getTitle());
        schedule.setDescription(requestDto.getDescription());
        schedule.setCreatedBy(userRepository.findById(requestDto.getCreatedById()).orElseThrow(() -> new IllegalArgumentException("not found")));
        // 참여 멤버 반환 로직 추가
        Schedule savedSchedule = scheduleRepository.save(schedule);

        List<Long> userIds = requestDto.getUserIds();
        for(Long userId : userIds){
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new RuntimeException("User with id " + userId + " not found")
            );
            UserSchedule userSchedule = new UserSchedule();
            userSchedule.setUser(user);
            userSchedule.setSchedule(savedSchedule);
            userScheduleRepository.save(userSchedule);
        }

        return new ScheduleResponseDto(savedSchedule);
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Schedule not found")
        );
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
