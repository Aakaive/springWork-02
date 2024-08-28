package com.sparta.springwork02.repository;

import com.sparta.springwork02.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    Page<Schedule> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
