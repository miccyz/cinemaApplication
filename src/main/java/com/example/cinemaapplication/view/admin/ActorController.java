package com.example.cinemaapplication.view.admin;

import com.example.cinemaapplication.dto.actor.ActorAddDto;
import com.example.cinemaapplication.dto.actor.ActorEditDto;
import com.example.cinemaapplication.exception.actor.ActorNotFound;
import com.example.cinemaapplication.sevice.admin.ActorSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminActorController")
@RequiredArgsConstructor
@RequestMapping("admin/actor")
public class ActorController {

    private final ActorSFC actorSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return actorSFC.displayList(model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return actorSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(ActorAddDto addDto, Model model) {
        return actorSFC.add(addDto, model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer id, Model model) throws ActorNotFound {
        return actorSFC.displayEditForm(id, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(ActorEditDto editDto, Model model) throws ActorNotFound {
        return actorSFC.edit(editDto, model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws ActorNotFound {
        return actorSFC.remove(id);
    }
}
