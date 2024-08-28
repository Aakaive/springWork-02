package com.sparta.springwork02.repository;

import com.sparta.springwork02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
