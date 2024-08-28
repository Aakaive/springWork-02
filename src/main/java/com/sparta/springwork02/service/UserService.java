package com.sparta.springwork02.service;

import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.dto.UserRequestDto;
import com.sparta.springwork02.dto.UserResponseDto;
import com.sparta.springwork02.entity.Schedule;
import com.sparta.springwork02.entity.User;
import com.sparta.springwork02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        return new UserResponseDto(user);
    }
}
