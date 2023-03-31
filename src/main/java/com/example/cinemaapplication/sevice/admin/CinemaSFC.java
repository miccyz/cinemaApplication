package com.example.cinemaapplication.sevice.admin;

import com.example.cinemaapplication.dto.cinema.CinemaAddDto;
import com.example.cinemaapplication.dto.cinema.CinemaDto;
import com.example.cinemaapplication.dto.cinema.CinemaEditDto;
import com.example.cinemaapplication.exception.cinema.CinemaNotFound;
import com.example.cinemaapplication.mapper.CinemaMapper;
import com.example.cinemaapplication.model.Cinema;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.sevice.basic.CinemaService;
import com.example.cinemaapplication.sevice.basic.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Service("adminCinemaSFC")
@RequiredArgsConstructor
public class CinemaSFC {

    private final CinemaService cinemaService;
    private final MessageService messageService;
    private final CinemaMapper cinemaMapper;

    public ModelAndView displayList(Model model) {
        List<Cinema> cinemaList = cinemaService.findAll();
        List<CinemaDto> dtoList = cinemaList.stream().map(cinemaMapper::toDto).collect(Collectors.toList());
        model.addAttribute("cinemaList", dtoList);
        return new ModelAndView("admin/cinema/list");
    }

    public ModelAndView displayAddForm(Model model) {
        model.addAttribute("addDto", new CinemaAddDto());
        return new ModelAndView("admin/cinema/add");
    }

    public ModelAndView add(CinemaAddDto addDto, Model model) {
        Cinema toSave = cinemaMapper.toNewModel(addDto);
        cinemaService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayAddForm(model);
    }

    public ModelAndView displayEditForm(Integer id, Model model) throws CinemaNotFound {
        Cinema cinema = cinemaService.findById(id);
        CinemaEditDto editDto = cinemaMapper.toEditDto(cinema);
        model.addAttribute("editDto", editDto);
        return new ModelAndView("admin/cinema/edit");
    }

    public ModelAndView edit(CinemaEditDto editDto, Model model) throws CinemaNotFound {
        Cinema cinema = cinemaService.findById(editDto.getId());
        Cinema toSave = cinemaMapper.toEditModel(cinema, editDto);
        cinemaService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }

    public ModelAndView remove(Integer id) throws CinemaNotFound {
        Cinema cinema = cinemaService.findById(id);
        for (Movie i : cinema.getMovieList()) {
            i.setCinema(null);
        }

        cinemaService.remove(cinema);
        return new ModelAndView("redirect:list");
    }
}
