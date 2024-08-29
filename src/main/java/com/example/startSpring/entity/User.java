package com.example.startSpring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "users")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;

//    @NotNull
//    @Size(min = 1, max = 20)
     String username;

//    @NotNull
//    @Size(min = 1, max = 20)
     String password;

//    @NotNull
//    @Size(min = 1, max = 50)
     String firstName;

//    @NotNull
//    @Size(min = 1, max = 50)
     String lastName;

     LocalDate dob;

     String _token;

}
