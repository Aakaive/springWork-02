package com.sparta.springwork02.controller;

import com.sparta.springwork02.dto.ScheduleResponseDto;
import com.sparta.springwork02.dto.UserRequestDto;
import com.sparta.springwork02.dto.UserResponseDto;
import com.sparta.springwork02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.saveUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto responseDto = userService.findUserById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
