package com.example.blurtle.ResponseDTO;

import com.example.blurtle.Entity.User;

import java.time.LocalDate;

public record UserResponseDTO(
        Long userId,
        String firstName,
        String lastName,
        String email,
        Long longestStreak,
        Long currentStreak,
        LocalDate lastSolved
) {
    public UserResponseDTO(User user) {
        this(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getLongestStreak(),
            user.getCurrentStreak(),
            user.getLastSolved()
        );
    }
}