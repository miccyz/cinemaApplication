package com.example.cinemaapplication.view.admin;

import com.example.cinemaapplication.dto.user.UserEditDto;
import com.example.cinemaapplication.exception.user.UserNotFound;
import com.example.cinemaapplication.sevice.admin.UserSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminUserController")
@RequiredArgsConstructor
@RequestMapping("admin/user")
public class UserController {

    private final UserSFC userSFC;

    @GetMapping("list")
    public ModelAndView displayUserList(Model model) {
        return userSFC.displayUserList(model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer userId, Model model) throws UserNotFound {
        return userSFC.displayEditForm(userId, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(UserEditDto editDto, Model model) throws UserNotFound {
        return userSFC.edit(editDto, model);
    }

    @PostMapping("changeStatus")
    public ModelAndView changeStatus(Integer userId) throws UserNotFound {
        return userSFC.changeStatus(userId);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer userId) throws UserNotFound {
        return userSFC.remove(userId);
    }
}
