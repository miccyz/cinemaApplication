package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.dto.user.LoggedUserDto;
import com.example.cinemaapplication.exception.user.UserNotFound;
import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public void remove(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer userId) throws UserNotFound {
        return userRepository.findById(userId).orElseThrow(UserNotFound::new);
    }

    public User findBySession(HttpServletRequest request) throws UserNotFound {
        LoggedUserDto loggedUserDto = (LoggedUserDto) request.getSession().getAttribute("loggedUser");
        return findById(loggedUserDto.getId());
    }

    public User findByEmail(String email) throws UserNotFound {
        return userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
