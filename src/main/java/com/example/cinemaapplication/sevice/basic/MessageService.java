package com.example.cinemaapplication.sevice.basic;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MessageService {

    public void responseMessage(boolean isSuccess, String message, Model model) {
        model.addAttribute("messageType", isSuccess ? "alert-success" : "alert-danger");
        model.addAttribute("message", message);
    }
}
