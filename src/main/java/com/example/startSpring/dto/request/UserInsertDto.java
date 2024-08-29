package com.example.startSpring.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.startSpring.entity.User}
 */
@Value
@Data
@Builder
public class UserInsertDto implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @NotNull
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    LocalDate dob;
}