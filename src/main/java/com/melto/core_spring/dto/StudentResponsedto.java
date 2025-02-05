package com.melto.core_spring.dto;

public record StudentResponsedto(
        String firstName,
        String lastName,
        String email,
        Integer age
) {
}
