package com.melto.core_spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Studentdto(
        @NotBlank(message = "first name is required")
        String firstName,

        @NotBlank(message = "last name is required")
        String lastName,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be from gmail.com domain")
        String email,

        @NotNull(message = "age field is required")
        Integer age,

        @NotNull(message = "school is required")
        Long id
) {

}
