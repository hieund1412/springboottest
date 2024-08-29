package com.example.startSpring.dto.request;

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
public class UserDto implements Serializable {
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}