package com.example.cinemaapplication.sevice.admin;

import com.example.cinemaapplication.dto.actor.ActorAddDto;
import com.example.cinemaapplication.dto.actor.ActorDto;
import com.example.cinemaapplication.dto.actor.ActorEditDto;
import com.example.cinemaapplication.exception.actor.ActorNotFound;
import com.example.cinemaapplication.mapper.ActorMapper;
import com.example.cinemaapplication.model.Actor;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.sevice.basic.ActorService;
import com.example.cinemaapplication.sevice.basic.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Service("adminActorSFC")
@RequiredArgsConstructor
public class ActorSFC {

    private final ActorService actorService;
    private final MessageService messageService;
    private final ActorMapper actorMapper;

    public ModelAndView displayList(Model model) {
        List<Actor> actors = actorService.findAll();
        List<ActorDto> dtoList = actors.stream().map(actorMapper::toDto).collect(Collectors.toList());
        model.addAttribute("actorList", dtoList);
        return new ModelAndView("admin/actor/list");
    }

    public ModelAndView displayAddForm(Model model) {
        model.addAttribute("addDto", new ActorAddDto());
        return new ModelAndView("admin/actor/add");
    }

    public ModelAndView add(ActorAddDto addDto, Model model) {
        Actor toSave = actorMapper.toNewModel(addDto);
        actorService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayAddForm(model);
    }

    public ModelAndView displayEditForm(Integer id, Model model) throws ActorNotFound {
        Actor actor = actorService.findById(id);
        ActorEditDto editDto = actorMapper.toEditDto(actor);
        model.addAttribute("editDto", editDto);
        return new ModelAndView("admin/actor/edit");
    }

    public ModelAndView edit(ActorEditDto editDto, Model model) throws ActorNotFound {
        Actor actor = actorService.findById(editDto.getId());
        Actor toSave = actorMapper.toEditModel(actor, editDto);
        actorService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }

    public ModelAndView remove(Integer id) throws ActorNotFound {
        Actor actor = actorService.findById(id);
        for (Movie i : actor.getMovieList()) {
            i.getActorList().remove(actor);
        }

        actorService.remove(actor);
        return new ModelAndView("redirect:list");
    }
}
