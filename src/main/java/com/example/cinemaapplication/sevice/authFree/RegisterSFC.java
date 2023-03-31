package com.example.cinemaapplication.sevice.authFree;

import com.example.cinemaapplication.dto.user.UserAddDto;
import com.example.cinemaapplication.exception.user.UserEmailAlreadyExists;
import com.example.cinemaapplication.exception.user.UserInvalidPassword;
import com.example.cinemaapplication.mapper.UserMapper;
import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.sevice.basic.MessageService;
import com.example.cinemaapplication.sevice.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class RegisterSFC {

    private final UserService userService;
    private final MessageService messageService;
    private final UserMapper userMapper;

    public ModelAndView displayRegisterForm(Model model) {
        model.addAttribute("addDto", new UserAddDto());
        return new ModelAndView("authFree/register");
    }

    public ModelAndView displayRegisterForm(UserAddDto addDto, Model model) {
        model.addAttribute("addDto", addDto);
        return new ModelAndView("authFree/register");
    }

    public ModelAndView register(UserAddDto addDto, Model model) {
        try {
            register(addDto);
            messageService.responseMessage(true, "Zarejestrowano", model);
            return displayRegisterForm(model);
        } catch (UserEmailAlreadyExists e) {
            messageService.responseMessage(false, "Email już istnieje", model);
        } catch (UserInvalidPassword e) {
            messageService.responseMessage(false, "Hasła są różne", model);
        }
        return displayRegisterForm(addDto, model);
    }

    private User register(UserAddDto addDto) throws UserEmailAlreadyExists, UserInvalidPassword {
        if (userService.isEmailAlreadyExists(addDto.getEmail())) {
            throw new UserEmailAlreadyExists();
        }

        if (!addDto.getPassword().equals(addDto.getPasswordConfirmation())) {
            throw new UserInvalidPassword();
        }

        User user = userMapper.toNewModel(addDto);
        return userService.save(user);
    }
}
