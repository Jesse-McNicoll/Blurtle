package com.example.blurtle.ResponseDTO;

import java.time.LocalDate;

public record UserResponseDTO(
        Long userId,
        String firstName,
        String lastName,
        String email,
        Long longestStreak,
        Long currentStreak,
        LocalDate lastSolved
) {}