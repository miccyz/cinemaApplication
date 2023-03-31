package com.example.cinemaapplication.dto.user;

import com.example.cinemaapplication.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEditDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
    private boolean isActive;
    private UserRole role;
}
