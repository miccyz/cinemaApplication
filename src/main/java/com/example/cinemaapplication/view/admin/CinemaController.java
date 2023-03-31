package com.example.cinemaapplication.view.admin;

import com.example.cinemaapplication.dto.cinema.CinemaAddDto;
import com.example.cinemaapplication.dto.cinema.CinemaEditDto;
import com.example.cinemaapplication.exception.cinema.CinemaNotFound;
import com.example.cinemaapplication.sevice.admin.CinemaSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminCinemaController")
@RequiredArgsConstructor
@RequestMapping("admin/cinema")
public class CinemaController {

    private final CinemaSFC cinemaSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return cinemaSFC.displayList(model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return cinemaSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(CinemaAddDto addDto, Model model) {
        return cinemaSFC.add(addDto, model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer id, Model model) throws CinemaNotFound {
        return cinemaSFC.displayEditForm(id, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(CinemaEditDto editDto, Model model) throws CinemaNotFound {
        return cinemaSFC.edit(editDto, model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws CinemaNotFound {
        return cinemaSFC.remove(id);
    }
}
