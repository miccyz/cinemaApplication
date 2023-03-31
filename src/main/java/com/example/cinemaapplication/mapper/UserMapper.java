package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.user.LoggedUserDto;
import com.example.cinemaapplication.dto.user.UserAddDto;
import com.example.cinemaapplication.dto.user.UserDto;
import com.example.cinemaapplication.dto.user.UserEditDto;
import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isActive(),
                user.getRole()
        );
    }

    public UserEditDto toEditDto(User user) {
        return new UserEditDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                "",
                "",
                user.isActive(),
                user.getRole()
        );
    }

    public User toEditModel(User user, UserEditDto editDto) {
        user.setRole(editDto.getRole());
        return user;
    }

    public LoggedUserDto toLoggedUserDto(User user) {
        return new LoggedUserDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toNewModel(UserAddDto addDto) {
        return new User(
                null,
                addDto.getFirstName(),
                addDto.getLastName(),
                addDto.getEmail(),
                addDto.getPassword(),
                true,
                UserRole.USER,
                Collections.emptyList()
        );
    }
}
