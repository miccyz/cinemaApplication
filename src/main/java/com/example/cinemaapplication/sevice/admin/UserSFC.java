package com.example.cinemaapplication.sevice.admin;

import com.example.cinemaapplication.dto.user.UserDto;
import com.example.cinemaapplication.dto.user.UserEditDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service("adminUserSFC")
@RequiredArgsConstructor
public class UserSFC {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessageService messageService;

    public ModelAndView displayUserList(Model model) {
        List<User> users = userService.findAll();
        List<UserDto> dtoList = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        model.addAttribute("userList", dtoList);
        return new ModelAndView("admin/user/list");
    }

    public ModelAndView changeStatus(Integer userId) throws UserNotFound {
        User user = userService.findById(userId);
        user.setActive(!user.isActive());
        userService.save(user);
        return new ModelAndView("redirect:list");
    }

    public ModelAndView remove(Integer userId) throws UserNotFound {
        User user = userService.findById(userId);
        userService.remove(user);
        return new ModelAndView("redirect:list");
    }

    public ModelAndView displayEditForm(Integer userId, Model model) throws UserNotFound {
        User user = userService.findById(userId);
        UserEditDto editDto = userMapper.toEditDto(user);
        model.addAttribute("editDto", editDto);
        model.addAttribute("userRoles", UserRole.values());
        return new ModelAndView("admin/user/edit");
    }

    public ModelAndView edit(UserEditDto editDto, Model model) throws UserNotFound {
        User user = userService.findById(editDto.getId());
        User toSave = userMapper.toEditModel(user, editDto);
        userService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }
}
