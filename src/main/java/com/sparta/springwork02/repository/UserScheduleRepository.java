package com.sparta.springwork02.repository;

import com.sparta.springwork02.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
