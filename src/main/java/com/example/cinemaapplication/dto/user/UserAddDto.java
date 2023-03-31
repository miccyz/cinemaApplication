package com.example.cinemaapplication.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAddDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
}
