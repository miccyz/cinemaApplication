package com.example.cinemaapplication.sevice.authFree;

import com.example.cinemaapplication.dto.user.LoggedUserDto;
import com.example.cinemaapplication.exception.user.UserBlocked;
import com.example.cinemaapplication.exception.user.UserInvalidPassword;
import com.example.cinemaapplication.exception.user.UserNotFound;
import com.example.cinemaapplication.mapper.UserMapper;
import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.model.UserRole;
import com.example.cinemaapplication.sevice.basic.MessageService;
import com.example.cinemaapplication.sevice.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LoginSCF {

    private final UserService userService;
    private final MessageService messageService;
    private final UserMapper userMapper;

    public ModelAndView login(String email, String password, HttpServletRequest request, Model model) {
        try {
            User user = userService.findByEmail(email);
            if (!user.getPassword().equals(password)) throw new UserInvalidPassword();
            if (!user.isActive()) throw new UserBlocked();

            LoggedUserDto dto = userMapper.toLoggedUserDto(user);
            request.getSession().setAttribute("loggedUser", dto);

            if (user.getRole() == UserRole.ADMIN) {
                return new ModelAndView("redirect:/admin/user/list");
            } else {
                return new ModelAndView("redirect:/authFree/movie/list");
            }
        } catch (UserNotFound | UserInvalidPassword e) {
            messageService.responseMessage(false, "Nieprawidłowe dane", model);
            return new ModelAndView("authFree/login");
        } catch (UserBlocked e) {
            messageService.responseMessage(false, "Użytkownik zablokowany", model);
            return new ModelAndView("authFree/login");
        }
    }
}
