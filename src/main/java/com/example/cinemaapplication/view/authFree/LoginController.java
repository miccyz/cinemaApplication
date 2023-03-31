package com.example.cinemaapplication.view.authFree;

import com.example.cinemaapplication.sevice.authFree.LoginSCF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginSCF loginSFC;

    @GetMapping("login")
    public ModelAndView displayLoginForm(){
        return new ModelAndView("authFree/login");
    }

    @PostMapping("login")
    public ModelAndView login(String email, String password, HttpServletRequest request, Model model){
        return loginSFC.login(email, password, request, model);
    }
}
