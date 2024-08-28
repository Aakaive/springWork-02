package com.sparta.springwork02.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "createdBy")
    private List<Schedule> createdSchedules = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserSchedule> userSchedules = new ArrayList<>();
}
