package com.codefinity.cacheexample.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
}
